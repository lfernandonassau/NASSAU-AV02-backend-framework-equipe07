package com.lanchonete.fastfood_app.dto;

import com.lanchonete.fastfood_app.model.ItemPedido;
import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoResponseDTO {

    private String produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private double precoUnitario;
    private double subTotal;

    public ItemPedidoResponseDTO(ItemPedido item) {
        this.produtoId = item.getProduto().getId().toString();
        this.nomeProduto = item.getProduto().getNome();
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPrecoUnitario();
        this.subTotal = item.getPrecoUnitario() * item.getQuantidade();
    }
}
