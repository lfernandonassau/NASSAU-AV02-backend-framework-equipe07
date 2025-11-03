package com.lanchonete.fastfood_app.Repository;

import com.lanchonete.fastfood_app.Model.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, UUID> {

}