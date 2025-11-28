package com.lanchonete.fastfood_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorCadastroDTO {
    private java.util.UUID usuarioId;
    private String telefone;
    private String veiculo;
    private String placa;
}
