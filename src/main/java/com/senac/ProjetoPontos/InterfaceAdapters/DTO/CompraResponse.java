package com.senac.ProjetoPontos.InterfaceAdapters.DTO;

public class CompraResponse {

    private String nomeProduto;
    private Integer quantidade;

    public CompraResponse(String nomeProduto, Integer quantidade) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
