package com.rekor.pedidos.controller;

import com.rekor.pedidos.model.Produto;
import com.rekor.pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public Optional<Produto> buscarProduto(@PathVariable Integer id) {
        return produtoService.buscarProduto(id);
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto cadastrado = produtoService.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrado);
    }

    @PutMapping("/{id}")
    public Optional<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto dados) {
        return produtoService.atualizarProduto(id, dados);
    }

    @DeleteMapping("/{id}")
    public boolean deletarProduto(@PathVariable Integer id) {
        return produtoService.deletarProduto(id);
    }
}
