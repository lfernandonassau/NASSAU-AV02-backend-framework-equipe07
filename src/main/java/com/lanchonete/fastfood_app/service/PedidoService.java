package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.dto.ItemPedidoRequestDTO;
import com.lanchonete.fastfood_app.dto.PedidoRequestDTO;
import com.lanchonete.fastfood_app.dto.PedidoResponseDTO;
import com.lanchonete.fastfood_app.model.ItemPedido;
import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.model.Produto;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.model.enums.StatusPedido;
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

    public List<PedidoResponseDTO> listarPedidos(){
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public PedidoResponseDTO buscarPorId(UUID id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return new PedidoResponseDTO(pedido);
    }

    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto){

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setTaxaEntrega(9.99);

        List<ItemPedido> itens = new ArrayList<>();
        double valorProdutos = 0.0;

        for (ItemPedidoRequestDTO itemDTO : dto.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId()));

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

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return new PedidoResponseDTO(pedidoSalvo);
    }

    public PedidoResponseDTO atualizarStatus(UUID id, StatusPedido novoStatus) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(novoStatus);

        if (novoStatus == StatusPedido.PRONTO) {
            entregadorService.atribuirEntregador(pedido);
        }

        if (novoStatus == StatusPedido.ENTREGUE) {
            notaFiscalService.gerarNotaFiscal(pedido);
        }

        Pedido atualizado = pedidoRepository.save(pedido);

        return new PedidoResponseDTO(atualizado);
    }
}
