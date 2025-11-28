package com.lanchonete.fastfood_app.repository;

import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.model.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByStatus(StatusPedido status);
}
