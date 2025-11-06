package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.model.Entregador;
import com.lanchonete.fastfood_app.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository repository;

    public List<Entregador> listarEntregadores(){
        return (List<Entregador>) repository.findAll();
    }

    public Entregador salvarEntregador(Entregador entregador){
        return repository.save(entregador);
    }
}
