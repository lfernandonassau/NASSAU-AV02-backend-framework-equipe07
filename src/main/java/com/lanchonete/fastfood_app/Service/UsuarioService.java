package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

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
