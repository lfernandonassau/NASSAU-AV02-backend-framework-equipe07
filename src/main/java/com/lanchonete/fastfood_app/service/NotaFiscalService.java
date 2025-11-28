package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.dto.NotaFiscalPublicDTO;
import com.lanchonete.fastfood_app.model.NotaFiscal;
import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.repository.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;


    public NotaFiscalPublicDTO buscarNotaFiscalPorPedidoId(UUID pedidoId, UUID usuarioIdLogado, String role) {

        NotaFiscal nota = notaFiscalRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Nota fiscal não encontrada"));

        Pedido pedido = nota.getPedido();

        if (!role.equals("ADMIN")) {

            if (role.equals("CLIENTE")) {
                if (!pedido.getUsuario().getId().equals(usuarioIdLogado)) {
                    throw new RuntimeException("Você não tem permissão para ver esta nota fiscal.");
                }
            }

            else {
                throw new RuntimeException("Acesso negado.");
            }
        }

        return new NotaFiscalPublicDTO(nota);
    }



    public NotaFiscal gerarNotaFiscal(Pedido pedido) {

        if (notaFiscalRepository.findByPedidoId(pedido.getId()).isPresent()) {
            throw new RuntimeException("Este pedido já possui nota fiscal gerada.");
        }

        NotaFiscal nota = new NotaFiscal();
        nota.setPedido(pedido);
        nota.setDataEmissao(LocalDateTime.now());
        nota.setValorProdutos(pedido.getValorProdutos());
        nota.setTaxaEntrega(pedido.getTaxaEntrega());
        nota.setValorTotal(pedido.getValorTotal());

        return notaFiscalRepository.save(nota);
    }
}
