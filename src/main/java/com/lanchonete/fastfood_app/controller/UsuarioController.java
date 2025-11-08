package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.dto.UsuarioRequestDTO;
import com.lanchonete.fastfood_app.dto.UsuarioResponseDTO;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //Registrar novo Usuario
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@RequestBody UsuarioRequestDTO dto) {
        Usuario novoUsuario = service.registrarUsuario(dto);
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(novoUsuario);

        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<Usuario> usuarios = service.listarUsuarios();

        List<UsuarioResponseDTO> dtos = usuarios.stream()
                .map(usuario -> new UsuarioResponseDTO(usuario))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable UUID id) {
        Usuario usuario = service.buscarPorId(id);
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(usuario);

        return ResponseEntity.ok(responseDTO);
    }

    }
}
