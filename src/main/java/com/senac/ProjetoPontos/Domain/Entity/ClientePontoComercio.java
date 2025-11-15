package com.senac.ProjetoPontos.Domain.Entity;

import java.util.UUID;

public class ClientePontoComercio {
    
    private UUID id;
    private Cliente cliente;
    private Comercio comercio;
    private int pontos;

    public ClientePontoComercio() {}

    public ClientePontoComercio(UUID id, Cliente cliente, Comercio comercio, int pontos) {
        this.id = id;
        this.cliente = cliente;
        this.comercio = comercio;
        this.pontos = pontos;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return "ClientePontoComercio{" +
                "id=" + id +
                ", cliente=" + cliente.getId() +
                ", comercio=" + comercio.getId() +
                ", pontos=" + pontos +
                '}';
    }
}