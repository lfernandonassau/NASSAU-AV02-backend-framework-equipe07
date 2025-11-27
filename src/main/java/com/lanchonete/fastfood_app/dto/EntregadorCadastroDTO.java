package com.lanchonete.fastfood_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorCadastroDTO {

    private UUID usuarioId;
    private String telefone;
    private String veiculo;
    private String placa;
}
