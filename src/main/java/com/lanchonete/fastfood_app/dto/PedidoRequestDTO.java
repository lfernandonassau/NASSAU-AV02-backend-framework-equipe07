package com.lanchonete.fastfood_app.dto;

import lombok.Data;
import java.util.List;

@Data
public class PedidoRequestDTO {
    private Long usuarioId;
    private List<ItemPedidoRequestDTO> itens;
}