package com.senac.ProjetoPontos.InterfaceAdapters.DTO;

import java.util.UUID;

public class CompraRequest {

    private UUID clienteId;
    private UUID produtoId;
    private Integer quantidade;

    public CompraRequest() {}

    public CompraRequest(UUID clienteId, UUID produtoId, Integer quantidade) {
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public UUID getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(UUID produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
