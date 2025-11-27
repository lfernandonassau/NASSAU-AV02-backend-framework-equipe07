package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.config.JwtUtil;
import com.lanchonete.fastfood_app.dto.EntregadorCadastroDTO;
import com.lanchonete.fastfood_app.dto.EntregadorResponseDTO;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.repository.UsuarioRepository;
import com.lanchonete.fastfood_app.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    @Autowired
    private EntregadorService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public EntregadorResponseDTO cadastrarEntregador(@RequestBody EntregadorCadastroDTO dto, @RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "");
        UUID usuarioId = UUID.fromString(jwtUtil.getUsuarioId(token));

        Usuario admin = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return service.cadastrarEntregador(dto, admin);
    }

    @PutMapping("/{id}")
    public EntregadorResponseDTO atualizarEntregador(@PathVariable UUID id, @RequestBody EntregadorCadastroDTO dto, @RequestHeader("Authorization") String tokenHeader
    ) {
        String token = tokenHeader.replace("Bearer ", "");
        UUID usuarioId = UUID.fromString(jwtUtil.getUsuarioId(token));

        Usuario admin = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return service.atualizarEntregador(id, dto, admin);
    }

    @GetMapping
    public List<EntregadorResponseDTO> listarTodos() {
        return service.listarEntregadores();
    }

    @GetMapping("/disponiveis")
    public List<EntregadorResponseDTO> listarDisponiveis() {
        return service.listarDisponiveis();
    }
}
