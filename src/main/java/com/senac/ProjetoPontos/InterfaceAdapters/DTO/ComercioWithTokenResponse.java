package com.senac.ProjetoPontos.InterfaceAdapters.DTO;

import com.senac.ProjetoPontos.Domain.Entity.Comercio;

public class ComercioWithTokenResponse {
    private Comercio comercio;
    private String token;

    public ComercioWithTokenResponse() {}

    public ComercioWithTokenResponse(Comercio comercio, String token) {
        this.comercio = comercio;
        this.token = token;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
