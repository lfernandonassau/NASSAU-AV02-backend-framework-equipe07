package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.dto.EntregadorRequestDTO;
import com.lanchonete.fastfood_app.dto.EntregadorResponseDTO;
import com.lanchonete.fastfood_app.model.Entregador;
import com.lanchonete.fastfood_app.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/entregador")
public class EntregadorController {

    @Autowired
    private EntregadorService service;

    @PostMapping
    public EntregadorResponseDTO cadastrarEntregador(@RequestBody EntregadorRequestDTO entregador) {
        return service.cadastrarEntregador(entregador);
    }

    @PutMapping("/{id}")
    public EntregadorResponseDTO atualizarEntregador(@PathVariable UUID id, @RequestBody EntregadorRequestDTO entregador) {
        return service.atualizarEntregador(id, entregador);
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
