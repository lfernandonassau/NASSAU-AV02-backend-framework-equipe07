package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.model.Entregador;
import com.lanchonete.fastfood_app.repository.EntregadorRepository;
import com.lanchonete.fastfood_app.dto.EntregadorRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository repository;

    public List<Entregador> listarEntregadores(){
        return (List<Entregador>) repository.findAll();
    }

    public Entregador cadastrarEntregador(Entregador entregador){
        return repository.save(entregador);
    }

    public List<Entregador> listarDisponiveis() {
        return repository.findAll();
    }

    public Entregador atualizarEntregador(UUID id, Entregador entregador) {
        return repository.findById(id).orElse(null);
    }

}
