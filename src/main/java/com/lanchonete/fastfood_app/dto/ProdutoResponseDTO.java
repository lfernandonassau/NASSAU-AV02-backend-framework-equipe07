package com.lanchonete.fastfood_app.dto;

import com.lanchonete.fastfood_app.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {

    private String id;
    private String nome;
    private String descricao;
    private Double preco;
    private String imagemUrl;

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId().toString();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.imagemUrl = produto.getImagemUrl();
    }
}
