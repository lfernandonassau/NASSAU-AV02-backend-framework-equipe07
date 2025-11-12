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

    private Pedido pedido;
    private Produto produto;
    private int quantidade;
    private double precoUnitario;

    public ItemPedidoResponseDTO(ItemPedido itemPedido) {
        this.pedido = getPedido();
        this.produto = getProduto();
        this.quantidade = getQuantidade();
        this.precoUnitario = getPrecoUnitario();
    }
}
