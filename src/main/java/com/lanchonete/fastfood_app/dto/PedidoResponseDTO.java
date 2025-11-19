package com.lanchonete.fastfood_app.dto;

import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.model.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {

    private String id;
    private String usuarioId;
    private Double valorProdutos;
    private Double valorTotal;
    private Double taxaEntrega;
    private StatusPedido status;
    private LocalDateTime dataCriacao;
    private List<ItemPedidoResponseDTO> itens;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId().toString();
        this.usuarioId = pedido.getUsuario().getId().toString();

        this.valorProdutos = pedido.getValorProdutos();
        this.valorTotal = pedido.getValorTotal();
        this.taxaEntrega = pedido.getTaxaEntrega();

        this.status = pedido.getStatus();
        this.dataCriacao = pedido.getDataCriacao();

        if (pedido.getItens() != null) {
            this.itens = pedido.getItens()
                    .stream()
                    .map(ItemPedidoResponseDTO::new)
                    .collect(Collectors.toList());
        }
    }
}
