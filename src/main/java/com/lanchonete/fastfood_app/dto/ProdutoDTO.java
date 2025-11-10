package com.lanchonete.fastfood_app.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String imagemUrl;
}
