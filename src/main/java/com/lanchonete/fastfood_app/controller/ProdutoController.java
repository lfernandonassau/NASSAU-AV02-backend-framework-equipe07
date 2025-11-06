package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.model.Produto;
import com.lanchonete.fastfood_app.model.Usuario;
import com.lanchonete.fastfood_app.service.ProdutoService;
import com.lanchonete.fastfood_app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> listarProdutos() {
        return service.listarProdutos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable UUID id){
        return service.buscarPorId(id);
    }

    @PostMapping
    public Produto salvarProduto(@RequestBody Produto produto) {
        return service.salvarProduto(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable UUID id, @RequestBody Produto produto){
        return service.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable UUID id) {
        service.excluirProduto(id);
    }
}
