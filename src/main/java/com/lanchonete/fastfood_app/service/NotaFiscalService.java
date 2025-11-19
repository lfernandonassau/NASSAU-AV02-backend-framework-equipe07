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

    public NotaFiscalPublicDTO buscarNotaFiscalPorPedidoId(UUID pedidoId) {
        NotaFiscal nota = notaFiscalRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Nota fiscal não encontrada"));
        return new NotaFiscalPublicDTO(nota);
    }

    public NotaFiscal gerarNotaFiscal(Pedido pedido) {

        NotaFiscal nota = new NotaFiscal();

        nota.setPedido(pedido);
        nota.setDataEmissao(LocalDateTime.now());
        nota.setValorProdutos(pedido.getValorProdutos());
        nota.setTaxaEntrega(pedido.getTaxaEntrega());
        nota.setValorTotal(pedido.getValorProdutos() + pedido.getTaxaEntrega());

        return notaFiscalRepository.save(nota);
    }


}
