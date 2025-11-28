package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.config.JwtUtil;
import com.lanchonete.fastfood_app.dto.LoginRequestDTO;
import com.lanchonete.fastfood_app.dto.LoginResponseDTO;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
            );

            Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            String token = jwtUtil.gerarToken(
                    usuario.getId().toString(),
                    usuario.getEmail(),
                    usuario.getTipo().name()
            );

            return new LoginResponseDTO(token);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciais inválidas.");
        }
    }
}
