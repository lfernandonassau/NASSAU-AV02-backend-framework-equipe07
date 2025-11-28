package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.dto.EntregadorCadastroDTO;
import com.lanchonete.fastfood_app.dto.EntregadorResponseDTO;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    @Autowired
    private EntregadorService service;

    @PostMapping
    public EntregadorResponseDTO cadastrarEntregador(
            @RequestBody EntregadorCadastroDTO dto,
            @AuthenticationPrincipal Usuario adminLogado) {

        return service.cadastrarEntregador(dto, adminLogado);
    }

    @PutMapping("/{id}")
    public EntregadorResponseDTO atualizarEntregador(
            @PathVariable UUID id,
            @RequestBody EntregadorCadastroDTO dto,
            @AuthenticationPrincipal Usuario adminLogado) {

        return service.atualizarEntregador(id, dto, adminLogado);
    }

    @GetMapping
    public List<EntregadorResponseDTO> listarTodos() {
        return service.listarEntregadores();
    }

    @GetMapping("/disponiveis")
    public List<EntregadorResponseDTO> listarDisponiveis() {
        return service.listarDisponiveis();
    }
}
