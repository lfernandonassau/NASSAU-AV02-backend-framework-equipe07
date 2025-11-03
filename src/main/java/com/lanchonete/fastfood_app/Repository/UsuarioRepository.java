package com.lanchonete.fastfood_app.Repository;

import com.lanchonete.fastfood_app.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

}