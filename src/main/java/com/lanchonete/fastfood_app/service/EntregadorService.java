package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.dto.EntregadorCadastroDTO;
import com.lanchonete.fastfood_app.dto.EntregadorResponseDTO;
import com.lanchonete.fastfood_app.model.Entregador;
import com.lanchonete.fastfood_app.model.Pedido;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.model.enums.TipoUsuario;
import com.lanchonete.fastfood_app.repository.EntregadorRepository;
import com.lanchonete.fastfood_app.repository.PedidoRepository;
import com.lanchonete.fastfood_app.repository.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<EntregadorResponseDTO> listarEntregadores() {
        return repository.findAll()
                .stream().map(EntregadorResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<EntregadorResponseDTO> listarDisponiveis() {
        return repository.findByDisponibilidadeTrue()
                .stream().map(EntregadorResponseDTO::new)
                .collect(Collectors.toList());
    }

    public EntregadorResponseDTO cadastrarEntregador(EntregadorCadastroDTO dto, Usuario admin) {

        if (admin.getTipo() != TipoUsuario.ADMIN) {
            throw new RuntimeException("Apenas administradores podem cadastrar entregadores.");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (repository.findByUsuarioId(usuario.getId()).isPresent()) {
            throw new RuntimeException("Este usuário já é entregador.");
        }

        usuario.setTipo(TipoUsuario.ENTREGADOR);
        usuarioRepository.save(usuario);

        Entregador novo = new Entregador();
        novo.setUsuario(usuario);
        novo.setTelefone(dto.getTelefone());
        novo.setVeiculo(dto.getVeiculo());
        novo.setPlaca(dto.getPlaca());
        novo.setDisponibilidade(true);

        Entregador salvo = repository.save(novo);

        return new EntregadorResponseDTO(salvo);
    }

    public EntregadorResponseDTO atualizarEntregador(UUID id, EntregadorCadastroDTO dto, Usuario admin) {

        if (admin.getTipo() != TipoUsuario.ADMIN) {
            throw new RuntimeException("Apenas administradores podem editar entregadores.");
        }

        Entregador entregador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado."));

        if (dto.getTelefone() != null) entregador.setTelefone(dto.getTelefone());
        if (dto.getVeiculo() != null) entregador.setVeiculo(dto.getVeiculo());
        if (dto.getPlaca() != null) entregador.setPlaca(dto.getPlaca());

        Entregador atualizado = repository.save(entregador);

        return new EntregadorResponseDTO(atualizado);
    }

    public void atribuirEntregador(Pedido pedido) {

        List<Entregador> disponiveis = repository.findByDisponibilidadeTrue();

        if (disponiveis.isEmpty()) {
            throw new RuntimeException("Nenhum entregador disponível no momento.");
        }

        Entregador entregador = disponiveis.get(0);

        entregador.setDisponibilidade(false);
        repository.save(entregador);

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
