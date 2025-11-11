package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.ProdutoUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Produto;
import com.senac.ProjetoPontos.Infrastructure.Security.UsuarioDetails;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoUseCase produtoUseCase;

    public ProdutoController(ProdutoUseCase produtoUseCase) {
        this.produtoUseCase = produtoUseCase;
    }

    @PutMapping("/desativar/{id}")
    public void desativarProduto(@PathVariable("id") String id) {
        produtoUseCase.desativarProdutoPorId(UUID.fromString(id));
    }

    @PutMapping("/ativar/{id}")
    public void ativarProduto(@PathVariable("id") String id) {
        produtoUseCase.ativarProdutoPorId(UUID.fromString(id));
    }

    @GetMapping("/{id}")
    public Produto obterProduto(@PathVariable("id") String id) {
        return produtoUseCase.obterProdutoPorId(UUID.fromString(id));
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto, Authentication authentication) {
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        UUID usuarioId = userDetails.getUsuario().getId();
        return produtoUseCase.criarProduto(produto, usuarioId);
    }

    @PutMapping
    public Produto atualizarProduto(@RequestBody Produto produto) {
        return produtoUseCase.atualizarProduto(produto);
    }
    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable("id") String id) {
        produtoUseCase.deletarProdutoPorId(UUID.fromString(id));
    }

    @GetMapping("/all")
    public java.util.List<Produto> listarProdutos() {
        return produtoUseCase.listarTodosProdutos();
    }

    @GetMapping("/meusprodutos")
    public java.util.List<Produto> listarProdutosPorComercio(Authentication authentication) {
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        UUID comercioId = userDetails.getUsuario().getId();
        return produtoUseCase.listarProdutosPorComercioId(comercioId);
    }

    @GetMapping("/comercio/{comercioId}")
    public java.util.List<Produto> listarProdutosPorComercioId(@PathVariable UUID comercioId) {
        return produtoUseCase.listarProdutosPorComercioId(comercioId);
    }

}
