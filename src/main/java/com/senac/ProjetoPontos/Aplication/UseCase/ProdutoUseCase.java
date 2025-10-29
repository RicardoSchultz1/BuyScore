package com.senac.ProjetoPontos.Aplication.UseCase;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Produto;
import com.senac.ProjetoPontos.Domain.Repository.ProdutoRepository;

@Service
public class ProdutoUseCase {

    private final ProdutoRepository produtoRepository;
    private final ComercioUseCase comercioUseCase;

    public ProdutoUseCase(ProdutoRepository produtoRepository, ComercioUseCase comercioUseCase) {
        this.produtoRepository = produtoRepository;
        this.comercioUseCase = comercioUseCase;
    }
    
    public void desativarProdutoPorId(UUID id) {
        var produto = produtoRepository.findById(id);
        if (produto != null) {
            produto.setAtivo(false);
            produtoRepository.update(produto);
        } else {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
    }

    public void ativarProdutoPorId(UUID id) {
        var produto = produtoRepository.findById(id);
        if (produto != null) {
            produto.setAtivo(true);
            produtoRepository.update(produto);
        } else {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
    }

    public Produto obterProdutoPorId(UUID id) {
        var produto = produtoRepository.findById(id);
        if (produto != null) {
            return produto;
        } else {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
    }

    public Produto criarProduto(Produto produto, UUID usuarioId) {
        produto.setComercio(comercioUseCase.buscarComercioPorUsuarioId(usuarioId));
        produto.setAtivo(true);
        return produtoRepository.save(produto);

    }

    public Produto atualizarProduto(Produto produto) {
        produtoRepository.update(produto);
        return produto;
    }

    public void deletarProdutoPorId(UUID id) {
        produtoRepository.delete(id);
    }

    public List<Produto> listarProdutosPorComercioId(UUID comercioId) {
        produtoRepository.findByComercioId(comercioId);
        return produtoRepository.findByComercioId(comercioId);
    }
}
