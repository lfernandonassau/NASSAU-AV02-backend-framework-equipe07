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
    private String usuarioId;
    private String usuarioNome;
    private String telefone;
    private String veiculo;
    private String placa;
    private boolean disponibilidade;

    // Construtor customizado a partir da entidade
    public EntregadorResponseDTO(Entregador e) {
        this.id = e.getId().toString();
        this.usuarioId = e.getUsuario() != null ? e.getUsuario().getId().toString() : null;
        this.usuarioNome = e.getUsuario() != null ? e.getUsuario().getNome() : null;
        this.telefone = e.getTelefone();
        this.veiculo = e.getVeiculo();
        this.placa = e.getPlaca();
        this.disponibilidade = e.isDisponibilidade();
    }
}
