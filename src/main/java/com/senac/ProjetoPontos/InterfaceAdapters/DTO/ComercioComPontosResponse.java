package com.senac.ProjetoPontos.InterfaceAdapters.DTO;

import java.util.UUID;

public class ComercioComPontosResponse {
    private UUID comercioId;
    private String razaoSocial;
    private String descricao;
    private String seguimento;
    private String fotoUsuario;
    private int pontosDoCliente;

    public ComercioComPontosResponse() {}

    public ComercioComPontosResponse(UUID comercioId, String razaoSocial, String descricao, 
                                   String seguimento, String fotoUsuario, int pontosDoCliente) {
        this.comercioId = comercioId;
        this.razaoSocial = razaoSocial;
        this.descricao = descricao;
        this.seguimento = seguimento;
        this.fotoUsuario = fotoUsuario;
        this.pontosDoCliente = pontosDoCliente;
    }

    public UUID getComercioId() {
        return comercioId;
    }

    public void setComercioId(UUID comercioId) {
        this.comercioId = comercioId;
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

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public int getPontosDoCliente() {
        return pontosDoCliente;
    }

    public void setPontosDoCliente(int pontosDoCliente) {
        this.pontosDoCliente = pontosDoCliente;
    }
}