package com.senac.ProjetoPontos.Domain.Entity;

import java.sql.Date;
import java.util.UUID;

public class Ponto {
    
    private UUID id;
    private String codigo;
    private int pontos;
    private Cliente cliente;
    private Comercio comercio;
    private Date data;

    public Ponto() {};

    public Ponto(String codigo, int pontos, Cliente cliente, Comercio comercio, Date data) {
        this.codigo = codigo;
        this.pontos = pontos;
        this.cliente = cliente;
        this.comercio = comercio;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public int getPontos() {
        return pontos;
    }
    public void setPontos(int pontos) {
        this.pontos = pontos;
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
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

}
