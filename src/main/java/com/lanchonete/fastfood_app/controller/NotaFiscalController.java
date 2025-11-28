package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.config.JwtUtil;
import com.lanchonete.fastfood_app.dto.NotaFiscalPublicDTO;
import com.lanchonete.fastfood_app.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/notafiscal")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService service;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/{pedidoId}")
    public NotaFiscalPublicDTO buscarNotaFiscalPorPedidoId(@PathVariable UUID pedidoId, @RequestHeader("Authorization") String tokenHeader
    ) {
        String token = tokenHeader.replace("Bearer ", "");
        UUID usuarioIdLogado = UUID.fromString(jwtUtil.getUsuarioId(token));
        String role = jwtUtil.getRole(token);

        return service.buscarNotaFiscalPorPedidoId(pedidoId, usuarioIdLogado, role);
    }
}
