package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.config.JwtUtil;
import com.lanchonete.fastfood_app.dto.PedidoRequestDTO;
import com.lanchonete.fastfood_app.dto.PedidoResponseDTO;
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

    @Autowired
    private JwtUtil jwtUtil;

    // CLIENTE cria pedidos
    @PostMapping
    public PedidoResponseDTO criarPedido(
            @RequestBody PedidoRequestDTO pedido,
            @RequestHeader("Authorization") String tokenHeader
    ) {
        String token = tokenHeader.replace("Bearer ", "");
        UUID usuarioId = UUID.fromString(jwtUtil.getUsuarioId(token));

        return service.criarPedido(pedido, usuarioId);
    }

    // ADMIN vê todos os pedidos
    @GetMapping
    public List<PedidoResponseDTO> listarPedidos() {
        return service.listarPedidos();
    }

    // CLIENTE vê apenas seus pedidos, ADMIN vê qualquer
    @GetMapping("/{id}")
    public PedidoResponseDTO buscarPorId(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String tokenHeader
    ) {
        String token = tokenHeader.replace("Bearer ", "");
        UUID usuarioId = UUID.fromString(jwtUtil.getUsuarioId(token));
        String role = jwtUtil.getRole(token);

        return service.buscarPorId(id, usuarioId, role);
    }

    // ADMIN muda status para PRONTO
    // ENTREGADOR muda para ENTREGUE
    @PutMapping("/{id}/status")
    public PedidoResponseDTO atualizarStatus(
            @PathVariable UUID id,
            @RequestParam StatusPedido novoStatus,
            @RequestHeader("Authorization") String tokenHeader
    ) {
        String token = tokenHeader.replace("Bearer ", "");
        UUID usuarioIdLogado = UUID.fromString(jwtUtil.getUsuarioId(token));
        String role = jwtUtil.getRole(token);

        return service.atualizarStatus(id, novoStatus, usuarioIdLogado, role);
    }
}
