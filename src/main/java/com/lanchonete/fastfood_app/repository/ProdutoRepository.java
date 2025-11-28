package com.lanchonete.fastfood_app.repository;

import com.lanchonete.fastfood_app.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, UUID>{
}