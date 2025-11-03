package com.lanchonete.fastfood_app.Repository;

import com.lanchonete.fastfood_app.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, UUID>{

}
