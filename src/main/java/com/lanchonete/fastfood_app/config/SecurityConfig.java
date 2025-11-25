package com.lanchonete.fastfood_app.config;

import com.lanchonete.fastfood_app.config.JwtFilter;
import com.lanchonete.fastfood_app.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // ALGORTIMO DE CRIPTOGRAFIA (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // INFORMA AO SPRING COMO AUTENTICAR USUÁRIOS
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // GERENCIA O LOGIN (UTILIZADO NO AUTHCONTROLLER)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // CONFIGURA A SEGURANÇA DA APLICAÇÃO
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeHttpRequests(auth -> auth
                // ROTAS LIBERADAS
                .requestMatchers("/auth/login", "/usuario").permitAll()

                // ROTAS PARA ADMIN (opcional)
                //.requestMatchers("/produto/**").hasRole("ADMIN")

                // TODAS AS OUTRAS PRECISAM DE TOKEN
                .anyRequest().authenticated()
        );

        // ADICIONA O FILTRO JWT ANTES DO FILTRO PADRÃO DO SPRING
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // Provider de autenticação
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
