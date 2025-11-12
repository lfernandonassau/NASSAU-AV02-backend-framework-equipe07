package com.lanchonete.fastfood_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoRequestDTO {
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private Double precoUnitario;
}