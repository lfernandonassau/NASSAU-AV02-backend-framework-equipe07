package com.lanchonete.fastfood_app.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String tipo; // CLIENTE, ADMIN, ENTREGADOR
