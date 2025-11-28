package com.lanchonete.fastfood_app.config;

import com.lanchonete.fastfood_app.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        // Captura o token corretamente
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            // Agora o token carrega email, id e role
            email = jwtUtil.getEmailFromToken(token);
        }

        // Se email existe e ainda não há autenticação no contexto
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Carrega o usuário pelo email
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            // Valida token e aplica autenticação
            if (jwtUtil.validarToken(token)) {

                // Cria o objeto de autenticação com as roles vindas do UserDetails
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities() // ROLE_ADMIN, ROLE_CLIENTE etc
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
