package com.lanchonete.fastfood_app.repository;

import com.lanchonete.fastfood_app.model.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, UUID> {
    List<Entregador> findByDisponibilidadeTrue();

    Optional<Entregador> findByUsuarioId(UUID usuarioId);
}
