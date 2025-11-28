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


    private String limparToken(String tokenHeader) {
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token inválido ou ausente!");
        }
        return tokenHeader.replace("Bearer ", "");
    }

    private UUID getUsuarioId(String tokenHeader) {
        String token = limparToken(tokenHeader);
        return UUID.fromString(jwtUtil.getUsuarioId(token));
    }

    private String getRole(String tokenHeader) {
        String token = limparToken(tokenHeader);
        return jwtUtil.getRole(token);
    }


    @PostMapping
    public PedidoResponseDTO criarPedido(
            @RequestBody PedidoRequestDTO pedido,
            @RequestHeader("Authorization") String tokenHeader
    ) {
        UUID usuarioId = getUsuarioId(tokenHeader);
        return service.criarPedido(pedido, usuarioId);
    }

    @GetMapping
    public List<PedidoResponseDTO> listarPedidos() {
        return service.listarPedidos();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscarPorId(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String tokenHeader
    ) {
        UUID usuarioId = getUsuarioId(tokenHeader);
        String role = getRole(tokenHeader);

        return service.buscarPorId(id, usuarioId, role);
    }

    @PutMapping("/{id}/status")
    public PedidoResponseDTO atualizarStatus(
            @PathVariable UUID id,
            @RequestParam StatusPedido novoStatus,
            @RequestHeader("Authorization") String tokenHeader
    ) {
        UUID usuarioIdLogado = getUsuarioId(tokenHeader);
        String role = getRole(tokenHeader);

        return service.atualizarStatus(id, novoStatus, usuarioIdLogado, role);
    }
}
