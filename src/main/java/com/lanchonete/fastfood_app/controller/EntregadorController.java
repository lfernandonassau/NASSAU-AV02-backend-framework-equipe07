package com.lanchonete.fastfood_app.controller;

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
    public Entregador cadastrarEntregador(@RequestBody Entregador entregador) {
        return service.cadastrarEntregador(entregador);
    }

    @PutMapping("/{id}")
    public Entregador atualizarEntregador(@PathVariable UUID id, @RequestBody Entregador entregador) {
        return service.atualizarEntregador(id, entregador);
    }

    @GetMapping
    public List<Entregador> listarTodos() {
        return service.listarEntregadores();
    }

    @GetMapping("/disponiveis")
    public List<Entregador> listarDisponiveis() {
        return service.listarDisponiveis();
    }
}
