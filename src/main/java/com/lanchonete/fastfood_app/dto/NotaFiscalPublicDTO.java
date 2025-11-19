package com.lanchonete.fastfood_app.dto;

import com.lanchonete.fastfood_app.model.NotaFiscal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscalPublicDTO {

    private String notaFiscalId;
    private String usuarioId;
    private String pedidoId;
    private Double valorProdutos;
    private Double taxaEntrega;
    private Double valorTotal;
    private LocalDateTime dataEmissao;

    public NotaFiscalPublicDTO(NotaFiscal nf) {
        this.notaFiscalId = nf.getId().toString();
        this.usuarioId = nf.getPedido().getUsuario().getId().toString();
        this.pedidoId = nf.getPedido().getId().toString();
        this.valorProdutos = nf.getValorProdutos();
        this.taxaEntrega = nf.getTaxaEntrega();
        this.valorTotal = nf.getValorTotal();
        this.dataEmissao = nf.getDataEmissao();
    }
}
