package com.lanchonete.fastfood_app.config;

import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.model.enums.TipoUsuario;
import com.lanchonete.fastfood_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        boolean adminExists = usuarioRepository.existsByTipo(TipoUsuario.ADMIN);
        if (!adminExists) {
            Usuario admin = new Usuario();
            admin.setNome("Admin");
            admin.setEmail("admin@lanchonete.com");
            admin.setSenha(passwordEncoder.encode("SenhaForte123!")); // senha inicial ADMIN
            admin.setTipo(TipoUsuario.ADMIN);

            usuarioRepository.save(admin);
            System.out.println("Admin inicial criado com sucesso!");
        }
    }
}
