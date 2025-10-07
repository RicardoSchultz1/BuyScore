package com.senac.ProjetoPontos.InterfaceAdapters.DTOs;

import java.util.UUID;

public class ComercioRequest {

    private UUID usuarioId;
    private String CNPJ;
    private String razaoSocial;
    private String descricao;
    private String seguimento;
    private UUID matrizId;

    public ComercioRequest() { }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
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

    public UUID getMatrizId() {
        return matrizId;
    }

    public void setMatrizId(UUID matrizId) {
        this.matrizId = matrizId;
    }
}
