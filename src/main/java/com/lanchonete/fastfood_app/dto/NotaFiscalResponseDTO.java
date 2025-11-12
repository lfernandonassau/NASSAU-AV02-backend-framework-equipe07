package com.lanchonete.fastfood_app.dto;

import com.lanchonete.fastfood_app.model.NotaFiscal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscalResponseDTO {
    private String numero;
    private LocalDateTime dataEmissao;
    private Double valorTotal;

    public NotaFiscalResponseDTO(NotaFiscal notaFiscal) {
        this.numero = notaFiscal.getNumero();
        this.dataEmissao = notaFiscal.getDataEmissao();
        this.valorTotal = notaFiscal.getValorTotal();
    }
}
