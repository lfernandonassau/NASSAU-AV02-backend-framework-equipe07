package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.repository.PedidoRepository;
import com.lanchonete.fastfood_app.dto.ItemPedidoDTO;
import com.lanchonete.fastfood_app.dto.PedidoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<Pedido> listarPedidos(){
        return (List<Pedido>) repository.findAll();
    }

    public Pedido salvarPedido(Pedido pedido){
        return repository.save(pedido);
    }
}
