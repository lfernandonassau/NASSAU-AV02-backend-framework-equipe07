package com.lanchonete.fastfood_app.dto;

import com.lanchonete.fastfood_app.model.Entregador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorResponseDTO {

    private String id;
    private String nome;
    private String telefone;
    private String veiculo;
    private String placa;
    private boolean disponibilidade;

    public EntregadorResponseDTO(Entregador entregador) {
        this.id = entregador.getId().toString();
        this.nome = entregador.getNome();
        this.telefone = entregador.getTelefone();
        this.veiculo = entregador.getVeiculo();
        this.placa = entregador.getPlaca();
        this.disponibilidade = entregador.isDisponibilidade();
    }
}
