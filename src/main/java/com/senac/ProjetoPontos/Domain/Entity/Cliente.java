package com.senac.ProjetoPontos.Domain.Entity;

import java.util.List;
import java.util.UUID;

public class Cliente {

    private UUID id;
    private Usuario usuario;
    private int pontos;
    private List<Comercio> comerciosFavoritos;
    private List<Produto> produtosFavoritos;

    public Cliente(UUID id, Usuario usuario, int pontos, List<Comercio> comerciosFavoritos, List<Produto> produtosFavoritos) {
        this.id = id;
        this.usuario = usuario;
        this.pontos = pontos;
        this.comerciosFavoritos = comerciosFavoritos;
        this.produtosFavoritos = produtosFavoritos;
    }

    public Cliente() { }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<Comercio> getComerciosFavoritos() {
        return comerciosFavoritos;
    }
    public void setComerciosFavoritos(List<Comercio> comerciosFavoritos) {
        this.comerciosFavoritos = comerciosFavoritos;
    }
    public List<Produto> getProdutosFavoritos() {
        return produtosFavoritos;
    }
    public void setProdutosFavoritos(List<Produto> produtosFavoritos) {
        this.produtosFavoritos = produtosFavoritos;
    }
    public int getPontos() {
        return pontos;
    }
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

}
