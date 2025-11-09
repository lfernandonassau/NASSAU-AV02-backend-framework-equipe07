package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.dto.PedidoRequestDTO;
import com.lanchonete.fastfood_app.dto.PedidoResponseDTO;
import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.model.enums.StatusPedido;
import com.lanchonete.fastfood_app.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody PedidoRequestDTO dto) {
        Pedido novoPedido = service.criarPedido(dto);
        PedidoResponseDTO responseDTO = new PedidoResponseDTO(novoPedido);

        return ResponseEntity.status(201).body(responseDTO);
    }
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        List<Pedido> pedidos = service.listarPedidos();
        List<PedidoResponseDTO> dtos = pedidos.stream()
                .map(PedidoResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable UUID id) {
        Pedido pedido = service.buscarPorId(id);
        PedidoResponseDTO responseDTO = new PedidoResponseDTO(pedido);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(@PathVariable UUID id, @RequestParam StatusPedido novoStatus) {
        Pedido pedidoAtualizado = service.atualizarStatus(id, novoStatus);
        PedidoResponseDTO responseDTO = new PedidoResponseDTO(pedidoAtualizado);

        return ResponseEntity.ok(responseDTO);
    }
}