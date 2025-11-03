package com.lanchonete.fastfood_app.Repository;

import com.lanchonete.fastfood_app.Model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, UUID> {

}