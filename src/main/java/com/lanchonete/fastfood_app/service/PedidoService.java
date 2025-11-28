package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.dto.ItemPedidoRequestDTO;
import com.lanchonete.fastfood_app.dto.PedidoRequestDTO;
import com.lanchonete.fastfood_app.dto.PedidoResponseDTO;
import com.lanchonete.fastfood_app.model.ItemPedido;
import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.model.Produto;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.model.enums.StatusPedido;
import com.lanchonete.fastfood_app.model.enums.TipoUsuario;
import com.lanchonete.fastfood_app.repository.PedidoRepository;
import com.lanchonete.fastfood_app.repository.ProdutoRepository;
import com.lanchonete.fastfood_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EntregadorService entregadorService;

    @Autowired
    private NotaFiscalService notaFiscalService;


    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll()
                .stream().map(PedidoResponseDTO::new)
                .collect(Collectors.toList());
    }


    public PedidoResponseDTO buscarPorId(UUID id, UUID usuarioId, String role) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (!role.equals("ADMIN")) {

            if (role.equals("CLIENTE")) {
                if (!pedido.getUsuario().getId().equals(usuarioId)) {
                    throw new RuntimeException("Você não pode visualizar pedidos de outros usuários.");
                }
            }

            if (role.equals("ENTREGADOR")) {
                if (pedido.getEntregador() == null ||
                        !pedido.getEntregador().getUsuario().getId().equals(usuarioId)) {

                    throw new RuntimeException("Você não é o entregador responsável por este pedido.");
                }
            }
        }

        return new PedidoResponseDTO(pedido);
    }


    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto, UUID usuarioIdLogado) {

        Usuario usuario = usuarioRepository.findById(usuarioIdLogado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (usuario.getTipo() != TipoUsuario.CLIENTE) {
            throw new RuntimeException("Somente clientes podem criar pedidos.");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setTaxaEntrega(9.99);

        List<ItemPedido> itens = new ArrayList<>();
        double valorProdutos = 0.0;

        for (ItemPedidoRequestDTO itemDTO : dto.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() ->
                            new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId()));

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());

            itens.add(item);
            valorProdutos += produto.getPreco() * itemDTO.getQuantidade();
        }

        pedido.setItens(itens);
        pedido.setValorProdutos(valorProdutos);
        pedido.setValorTotal(valorProdutos + pedido.getTaxaEntrega());

        return new PedidoResponseDTO(pedidoRepository.save(pedido));
    }


    public PedidoResponseDTO atualizarStatus(UUID id, StatusPedido novoStatus,
                                             UUID usuarioIdLogado, String role) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Usuario usuarioLogado = usuarioRepository.findById(usuarioIdLogado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        if (novoStatus == StatusPedido.PRONTO) {

            if (!role.equals("ADMIN")) {
                throw new RuntimeException("Apenas administradores podem marcar pedido como PRONTO.");
            }

            entregadorService.atribuirEntregador(pedido);
        }


        if (novoStatus == StatusPedido.ENTREGUE) {

            if (!role.equals("ENTREGADOR")) {
                throw new RuntimeException("Somente entregadores podem finalizar pedidos.");
            }

            if (pedido.getEntregador() == null) {
                throw new RuntimeException("Este pedido não possui entregador atribuído.");
            }

            if (!pedido.getEntregador().getUsuario().getId().equals(usuarioIdLogado)) {
                throw new RuntimeException("Você não é o entregador responsável por este pedido.");
            }

            notaFiscalService.gerarNotaFiscal(pedido);

            entregadorService.liberarEntregador(pedido);
        }


        if (role.equals("CLIENTE")) {
            throw new RuntimeException("Clientes não podem modificar o status do pedido.");
        }


        pedido.setStatus(novoStatus);
        Pedido atualizado = pedidoRepository.save(pedido);

        return new PedidoResponseDTO(atualizado);
    }
}
