package com.lanchonete.fastfood_app.dto;

import lombok.Data;

@Data
public class EntregadorRequestDTO {
    private String nome;
    private String telefone;
    private String veiculo;
    private String placa;
    private Boolean disponibilidade;
}