package com.senac.ProjetoPontos.InterfaceAdapters.DTO;

public class EstatisticaMensalResponse {
    
    private int mes;
    private int ano;
    private int quantidadeClientes;

    public EstatisticaMensalResponse() {}

    public EstatisticaMensalResponse(int mes, int ano, int quantidadeClientes) {
        this.mes = mes;
        this.ano = ano;
        this.quantidadeClientes = quantidadeClientes;
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

    public int getQuantidadeClientes() {
        return quantidadeClientes;
    }

    public void setQuantidadeClientes(int quantidadeClientes) {
        this.quantidadeClientes = quantidadeClientes;
    }
}