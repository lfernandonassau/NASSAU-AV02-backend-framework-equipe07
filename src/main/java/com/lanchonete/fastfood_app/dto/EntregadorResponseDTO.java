package com.lanchonete.fastfood_app.dto;

import lombok.Data;

@Data
public class EntregadorResponseDTO {
    private Long id;
    private String nome;
    private String veiculo;
    private Boolean disponibilidade;
}