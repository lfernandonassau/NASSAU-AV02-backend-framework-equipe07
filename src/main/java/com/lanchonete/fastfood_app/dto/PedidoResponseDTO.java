package com.lanchonete.fastfood_app.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponseDTO {
    private Long id;
    private LocalDateTime data;
    private Double valorTotal;
    private String status;
    private Double taxaEntrega;
    private UsuarioResponseDTO cliente;
    private EntregadorResponseDTO entregador;
    private List<ItemPedidoDTO> itens;
    private NotaFiscalPublicDTO notaFiscal;
}