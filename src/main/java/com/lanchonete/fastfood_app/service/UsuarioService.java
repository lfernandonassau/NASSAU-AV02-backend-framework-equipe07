package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.dto.UsuarioRequestDTO;
import com.lanchonete.fastfood_app.dto.UsuarioResponseDTO;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioResponseDTO> listarUsuarios() {
        return repository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(UUID id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioResponseDTO(usuario);
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEndereco(dto.getEndereco());

        Usuario salvo = repository.save(usuario);
        return new UsuarioResponseDTO(salvo);
    }
}
