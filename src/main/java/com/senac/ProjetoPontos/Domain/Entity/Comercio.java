package com.senac.ProjetoPontos.Domain.Entity;

import java.util.UUID;

public class Comercio {
    
    private UUID id;
    private Usuario usuario;
    private String cnpj;
    private String razaoSocial;
    private String descricao;
    private String seguimento;
    private Usuario matriz;
    private int vendas;

    public Comercio(UUID id, Usuario usuario, String cnpj, String razaoSocial, String descricao, String seguimento, Usuario matriz, int vendas) {
        this.id = id;
        this.usuario = usuario;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.descricao = descricao;
        this.seguimento = seguimento;
        this.matriz = matriz;
        this.vendas = vendas;
    }

    public Comercio() { }

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
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getSeguimento() {
        return seguimento;
    }
    public void setSeguimento(String seguimento) {
        this.seguimento = seguimento;
    }
    public Usuario getMatriz() {
        return matriz;
    }
    public void setMatriz(Usuario matriz) {
        this.matriz = matriz;
    }
    public int getVendas() {
        return vendas;
    }
    public void setVendas(int vendas) {
        this.vendas = vendas;
    }

}
