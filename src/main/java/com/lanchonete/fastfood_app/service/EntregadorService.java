package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.dto.EntregadorRequestDTO;
import com.lanchonete.fastfood_app.dto.EntregadorResponseDTO;
import com.lanchonete.fastfood_app.model.Entregador;
import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.repository.EntregadorRepository;
import com.lanchonete.fastfood_app.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<EntregadorResponseDTO> listarEntregadores() {
        return repository.findAll()
                .stream()
                .map(EntregadorResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<EntregadorResponseDTO> listarDisponiveis() {
        return repository.findByDisponibilidadeTrue()
                .stream()
                .map(EntregadorResponseDTO::new)
                .collect(Collectors.toList());
    }

    public EntregadorResponseDTO cadastrarEntregador(EntregadorRequestDTO dto) {

        Entregador novo = new Entregador();
        novo.setNome(dto.getNome());
        novo.setTelefone(dto.getTelefone());
        novo.setVeiculo(dto.getVeiculo());
        novo.setPlaca(dto.getPlaca());
        novo.setDisponibilidade(true); // sempre disponível ao cadastrar

        Entregador salvo = repository.save(novo);
        return new EntregadorResponseDTO(salvo);
    }

    public EntregadorResponseDTO atualizarEntregador(UUID id, EntregadorRequestDTO dto) {

        Entregador entregador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));

        if (dto.getNome() != null) entregador.setNome(dto.getNome());
        if (dto.getTelefone() != null) entregador.setTelefone(dto.getTelefone());
        if (dto.getVeiculo() != null) entregador.setVeiculo(dto.getVeiculo());
        if (dto.getPlaca() != null) entregador.setPlaca(dto.getPlaca());

        Entregador atualizado = repository.save(entregador);
        return new EntregadorResponseDTO(atualizado);
    }

    public void atribuirEntregador(Pedido pedido) {

        // Buscar entregadores disponíveis
        List<Entregador> disponiveis = repository.findByDisponibilidadeTrue();

        if (disponiveis.isEmpty()) {
            throw new RuntimeException("Nenhum entregador disponível no momento.");
        }

        // Selecionar o primeiro disponível
        Entregador entregador = disponiveis.get(0);

        // Marcar como ocupado
        entregador.setDisponibilidade(false);
        repository.save(entregador);

        // Associar ao pedido
        pedido.setEntregador(entregador);
        pedidoRepository.save(pedido);
    }

    public void liberarEntregador(Pedido pedido) {

        if (pedido.getEntregador() == null) return;

        Entregador e = pedido.getEntregador();

        e.setDisponibilidade(true);
        repository.save(e);
    }
}
