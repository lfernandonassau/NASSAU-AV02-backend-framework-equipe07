package com.lanchonete.fastfood_app.repository;

import com.lanchonete.fastfood_app.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, UUID> {
    Optional<NotaFiscal> findByPedidoId(UUID pedidoId);
}
