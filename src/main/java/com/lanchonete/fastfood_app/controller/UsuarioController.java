package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.dto.UsuarioRequestDTO;
import com.lanchonete.fastfood_app.dto.UsuarioResponseDTO;
import com.lanchonete.fastfood_app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public UsuarioResponseDTO cadastrarUsuario(@RequestBody UsuarioRequestDTO dto) {
        return service.cadastrarUsuario(dto);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios() {
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }
}
