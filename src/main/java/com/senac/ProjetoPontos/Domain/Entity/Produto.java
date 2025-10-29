package com.senac.ProjetoPontos.Domain.Entity;

import java.util.UUID;

public class Produto {
    
    private UUID id;
    private String nome;
    private String descricao;
    private int valor;
    private boolean ativo;
    private String fotoProduto;
    private Comercio comercio;

    public Produto(UUID id, String nome, String descricao, int valor, boolean ativo, String fotoProduto, Comercio comercio) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.ativo = ativo;
        this.fotoProduto = fotoProduto;
        this.comercio = comercio;
    }

    protected Produto() { }


    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValor() {
        return valor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getFotoProduto() {
        return fotoProduto;
    }

    public Comercio getComercio() {
        return comercio;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public void setFotoProduto(String fotoProduto) {
        this.fotoProduto = fotoProduto;
    }
    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }
}
