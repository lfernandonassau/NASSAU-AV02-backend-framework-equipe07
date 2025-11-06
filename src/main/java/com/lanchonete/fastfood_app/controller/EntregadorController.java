package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.model.Entregador;
import com.lanchonete.fastfood_app.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregador")
public class EntregadorController {

    @Autowired
    private EntregadorService service;

    @GetMapping
    public List<Entregador> listarEntregadores(){
        return (List<Entregador>) service.listarEntregadores();
    }

    @PostMapping
    public Entregador salvarEntregador(@RequestBody Entregador entregador){
        return service.salvarEntregador(entregador);
    }
}
