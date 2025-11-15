package com.senac.ProjetoPontos.InterfaceAdapters.DTO;

public class EstatisticaCompraMensalResponse {
    
    private int mes;
    private int ano;
    private int quantidadeCompras;

    public EstatisticaCompraMensalResponse() {}

    public EstatisticaCompraMensalResponse(int mes, int ano, int quantidadeCompras) {
        this.mes = mes;
        this.ano = ano;
        this.quantidadeCompras = quantidadeCompras;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(int quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
    }
}