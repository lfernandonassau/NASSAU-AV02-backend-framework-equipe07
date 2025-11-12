package com.lanchonete.fastfood_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {
    private UUID usuarioId;
    private List<ItemPedidoRequestDTO> itens;
}