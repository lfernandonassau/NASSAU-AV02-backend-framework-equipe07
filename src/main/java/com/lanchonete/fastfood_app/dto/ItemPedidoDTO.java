package com.lanchonete.fastfood_app.dto;

import lombok.Data;

@Data
public class ItemPedidoDTO {
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private Double precoUnitario;
}