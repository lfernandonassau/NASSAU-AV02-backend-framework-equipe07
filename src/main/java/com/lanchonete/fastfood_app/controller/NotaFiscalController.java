package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.dto.NotaFiscalPublicDTO;
import com.lanchonete.fastfood_app.model.NotaFiscal;
import com.lanchonete.fastfood_app.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/notafiscal")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService service;

    @GetMapping("/{pedidoId}")
    public ResponseEntity<NotaFiscalPublicDTO> buscarNotaPorPedidoId(@PathVariable UUID pedidoId) {

        NotaFiscal notaFiscal = service.buscarPorPedidoId(pedidoId);
        if (notaFiscal == null) {
            return ResponseEntity.notFound().build();
        }

        NotaFiscalPublicDTO responseDTO = new NotaFiscalPublicDTO(notaFiscal);

        return ResponseEntity.ok(responseDTO);
    }
}