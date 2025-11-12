package com.lanchonete.fastfood_app.dto;

import com.lanchonete.fastfood_app.model.Usuario;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private String id;
    private String nome;
    private String email;
    private String tipo;
    private String endereco;
    private String telefone;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId().toString();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.endereco = usuario.getEndereco();
        this.telefone = usuario.getTelefone();
    }
}