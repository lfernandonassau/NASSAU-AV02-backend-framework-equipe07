package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.dto.ProdutoRequestDTO;
import com.lanchonete.fastfood_app.dto.ProdutoResponseDTO;
import com.lanchonete.fastfood_app.model.Produto;
import com.lanchonete.fastfood_app.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ProdutoResponseDTO cadastrarProduto(@RequestBody ProdutoRequestDTO produto) {
        return service.cadastrarProduto(produto);
    }


    @GetMapping
    public List<ProdutoResponseDTO> listarProdutos() {
        return service.listarProdutos();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizarProduto(@PathVariable UUID id, @RequestBody ProdutoRequestDTO dto) {
        return service.atualizarProduto(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable UUID id) {
        service.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}
