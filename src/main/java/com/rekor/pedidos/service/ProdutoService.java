package com.rekor.pedidos.service;

import com.rekor.pedidos.model.Produto;
import com.rekor.pedidos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarProduto(Integer id) {
        return produtoRepository.findById(id);
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> atualizarProduto(Integer id, Produto dados) {
        return produtoRepository.findById(id).map( produto -> {
            produto.setTitulo(dados.getTitulo());
            produto.setDescricao(dados.getDescricao());
            produto.setPreco(dados.getPreco());

            return produtoRepository.save(produto);
        });
    }

    public boolean deletarProduto(Integer id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
