package com.lanchonete.fastfood_app.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24; // 24h

    // Chave secreta para assinatura (forte e segura)
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // GERAR TOKEN JWT ---------------------------------------------------------
    public String gerarToken(String usuarioId, String email, String role) {
        return Jwts.builder()
                .setSubject(usuarioId)           // ID do usuário
                .claim("email", email)           // adicional
                .claim("role", role)             // papel (CLIENTE, ADMIN, etc)
                .setIssuedAt(new Date())         // data emissão
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(secretKey)
                .compact();
    }

    // VALIDAR TOKEN -----------------------------------------------------------
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // PEGAR CLAIMS ------------------------------------------------------------
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // PEGAR ID DO USUÁRIO -----------------------------------------------------
    public String getUsuarioId(String token) {
        return getClaims(token).getSubject();
    }

    // PEGAR EMAIL -------------------------------------------------------------
    public String getEmailFromToken(String token) {
        return getClaims(token).get("email", String.class);
    }

    // PEGAR ROLE --------------------------------------------------------------
    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }
}
