package com.lanchonete.fastfood_app.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24; // 24h

    // 🔐 ATENÇÃO: chave fixa (não pode trocar a cada reinicialização)
    private static final String SECRET =
            "3D1A7F9C8B2E4C61397A1F2D4B6C8E0F3A7C9B1D2E4F6A8C0B2D4E6F8A1C3E5";

    private final Key secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(
            Base64.getEncoder().encodeToString(SECRET.getBytes())
    ));

    public String gerarToken(String usuarioId, String email, String role) {
        return Jwts.builder()
                .setSubject(usuarioId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

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

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsuarioId(String token) {
        return getClaims(token).getSubject();
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).get("email", String.class);
    }

    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }
}
