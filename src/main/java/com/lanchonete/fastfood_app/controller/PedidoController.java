package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.dto.PedidoRequestDTO;
import com.lanchonete.fastfood_app.dto.PedidoResponseDTO;
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
    public PedidoResponseDTO criarPedido(@RequestBody PedidoRequestDTO pedido) {
        return service.criarPedido(pedido);
    }

    @GetMapping
    public List<PedidoResponseDTO> listarPedidos() {
        return service.listarPedidos();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}/status")
    public PedidoResponseDTO atualizarStatus(@PathVariable UUID id, @RequestParam StatusPedido novoStatus) {
        return service.atualizarStatus(id, novoStatus);
    }
}
