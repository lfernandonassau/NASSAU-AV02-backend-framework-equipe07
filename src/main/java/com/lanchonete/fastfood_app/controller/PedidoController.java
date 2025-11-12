package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.model.enums.StatusPedido;
import com.lanchonete.fastfood_app.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public Pedido cadastrarPedido(@RequestBody Pedido pedido) {
        return service.cadastrarPedido(pedido);
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return service.listarPedidos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}/status")
    public Pedido atualizarStatus(@PathVariable UUID id, @RequestParam StatusPedido novoStatus) {
        return service.atualizarStatus(id, novoStatus);
    }
}
