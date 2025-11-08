package com.lanchonete.fastfood_app.controller;

import com.lanchonete.fastfood_app.dto.ProdutoDTO;
import com.lanchonete.fastfood_app.model.Produto;
import com.lanchonete.fastfood_app.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto novoProduto = service.criarProduto(dto);
        ProdutoDTO responseDTO = new ProdutoDTO(novoProduto);

        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProduto() {
        List<ProdutoDTO> dtos = produtos.stream()
                .map(ProdutoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable UUID id) {
        Produto produto = service.buscarPorId(id);
        ProdutoDTO responseDTO = new ProdutoDTO(produto);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvarProduto(@RequestBody Produto produto) {
        return service.salvarProduto(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable UUID id, @RequestBody Produto produto){
        Produto produtoAtualizado = service.atualizarProduto(id, dto);
        ProdutoDTO responseDTO = new ProdutoDTO(produtoAtualizado);

        return service.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable UUID id) {
        service.excluirProduto(id);

        return ResponseEntity.noContent().build();
    }
}
