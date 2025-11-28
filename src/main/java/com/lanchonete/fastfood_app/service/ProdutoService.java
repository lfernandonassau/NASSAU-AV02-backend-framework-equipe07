package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.dto.ProdutoRequestDTO;
import com.lanchonete.fastfood_app.dto.ProdutoResponseDTO;
import com.lanchonete.fastfood_app.model.Produto;
import com.lanchonete.fastfood_app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoResponseDTO> listarProdutos() {
        return repository.findAll()
                .stream()
                .map(ProdutoResponseDTO::new) // Construtor que recebe Produto
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(UUID id) {
        return repository.findById(id)
                .map(ProdutoResponseDTO::new)
                .orElse(null);
    }

    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto) {
        Produto novoProduto = new Produto();
        novoProduto.setNome(dto.getNome());
        novoProduto.setDescricao(dto.getDescricao());
        novoProduto.setPreco(dto.getPreco());
        novoProduto.setImagemUrl(dto.getImagemUrl()); // se o DTO tiver esse campo

        return new ProdutoResponseDTO(repository.save(novoProduto));
    }

    public ProdutoResponseDTO atualizarProduto(UUID id, ProdutoRequestDTO dto) {
        Produto produtoExistente = repository.findById(id).orElse(null);
        if (produtoExistente == null) {
            return null;
        }

        if (dto.getNome() != null) produtoExistente.setNome(dto.getNome());
        if (dto.getDescricao() != null) produtoExistente.setDescricao(dto.getDescricao());
        if (dto.getPreco() != null) produtoExistente.setPreco(dto.getPreco());
        if (dto.getImagemUrl() != null) produtoExistente.setImagemUrl(dto.getImagemUrl());

        return new ProdutoResponseDTO(repository.save(produtoExistente));
    }

    public void excluirProduto(UUID id) {
        repository.deleteById(id);
    }

}
