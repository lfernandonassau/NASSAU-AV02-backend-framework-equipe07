package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.dto.UsuarioRequestDTO;
import com.lanchonete.fastfood_app.dto.UsuarioResponseDTO;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.model.enums.TipoUsuario;
import com.lanchonete.fastfood_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public List<UsuarioResponseDTO> listarUsuarios() {
        return repository.findAll()
                .stream().map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(UUID id) {
        Usuario u = repository.findById(id).orElseThrow();
        return new UsuarioResponseDTO(u);
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {

        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já está registrado!");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEndereco(dto.getEndereco());
        usuario.setSenha(encoder.encode(dto.getSenha()));
        usuario.setTipo(TipoUsuario.CLIENTE);
        return new UsuarioResponseDTO(repository.save(usuario));
    }
}
