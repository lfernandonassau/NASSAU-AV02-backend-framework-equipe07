package com.lanchonete.fastfood_app.service;

import com.lanchonete.fastfood_app.model.Produto;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.repository.ProdutoRepository;
import com.lanchonete.fastfood_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> listarProdutos() {
        return (List<Produto>) repository.findAll();

    }

    public Produto buscarPorId(UUID id){
        return repository.findById(id).orElse(null);
    }

    public Produto salvarProduto(Produto produto) {
        return repository.save(produto);
    }

    public Produto atualizarProduto(UUID id, Produto produtoAtualizado) {
        Produto produtoExistente =  repository.findById(id).orElse(null);

        // Atualiza apenas os campos não nulos
        if (produtoAtualizado.getNome() != null) {
            produtoExistente.setNome(produtoAtualizado.getNome());
        }
        if (produtoAtualizado.getDescricao() != null) {
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        }
        if (produtoAtualizado.getPreco() != null) {
            produtoExistente.setPreco(produtoAtualizado.getPreco());
        }

        return repository.save(produtoExistente);

    }

    public void excluirProduto(UUID id) {
        repository.deleteById(id);
    }
}
