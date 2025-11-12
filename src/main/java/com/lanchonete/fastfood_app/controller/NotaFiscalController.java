package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.model.NotaFiscal;
import com.lanchonete.fastfood_app.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/notafiscal")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService service;

    @GetMapping("/{pedidoId}")
    public NotaFiscal buscarNotaPorPedidoId(@PathVariable UUID pedidoId) {
        return service.buscarPorPedidoId(pedidoId);
    }
}
