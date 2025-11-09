package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.dto.EntregadorRequestDTO;
import com.lanchonete.fastfood_app.dto.EntregadorResponseDTO;
import com.lanchonete.fastfood_app.model.Entregador;
import com.lanchonete.fastfood_app.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entregador")
public class EntregadorController {

    @Autowired
    private EntregadorService service;

    @PutMapping("/{id}")
    public ResponseEntity<EntregadorResponseDTO> atualizarEntregador(@PathVariable UUID id, @RequestBody EntregadorRequestDTO dto) {
        Entregador entregadorAtualizado = service.atualizarEntregador(id, dto);

        EntregadorResponseDTO responseDTO = new EntregadorResponseDTO(entregadorAtualizado);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<EntregadorResponseDTO> criarEntregador(@RequestBody EntregadorRequestDTO dto) {
        Entregador novoEntregador = service.criarEntregador(dto);
        EntregadorResponseDTO responseDTO = new EntregadorResponseDTO(novoEntregador);

        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<EntregadorRequestDTO>> listarTodos(){
        List<Entregador> entregadores = service.listarEntregadores();
        List<EntregadorResponseDTO> dtos = entregadores.stream()
                .map(EntregadorResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<EntregadorResponseDTO>> listarDisponiveis() {
        List<Entregador> entregadores = service.listarDisponiveis();
        List<EntregadorResponseDTO> dtos = entregadores.stream()
                .map(EntregadorResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
