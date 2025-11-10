package com.lanchonete.fastfood_app.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotaFiscalPublicDTO {
    private Long numero;
    private LocalDateTime dataEmissao;
    private Double valorTotal;
}