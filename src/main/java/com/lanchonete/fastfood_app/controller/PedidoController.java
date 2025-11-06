package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public List<Pedido> listarPedidos(){
        return service.listarPedidos();
    }

    @PostMapping
    public Pedido salvarPedido(@RequestBody Pedido pedido){
        return service.salvarPedido(pedido);
    }
}
