package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.repository.UsuarioRepository;
import com.lanchonete.fastfood_app.dto.UsuarioRequestDTO;
import com.lanchonete.fastfood_app.model.enums.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario registrarUsuario(UsuarioRequestDTO dto) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(dto.getSenha());
        novoUsuario.setEndereco(dto.getEndereco());
        novoUsuario.setTelefone(dto.getTelefone());
        novoUsuario.setTipo(TipoUsuario.CLIENTE);

        return repository.save(novoUsuario);
    }

    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) repository.findAll();
    }

    public Usuario buscarPorId(UUID id){
        return repository.findById(id).orElse(null);
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }
}
