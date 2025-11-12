package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.model.Usuario;
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
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return service.cadastrarUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }
}
