package com.lanchonete.fastfood_app.dto;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String nome;
    private String email;
    private String senha; // só usado na criação ou atualização
    private String tipo; // CLIENTE, ADMIN, ENTREGADOR
    private String endereco;
    private String telefone;
}