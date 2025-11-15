package com.senac.ProjetoPontos.InterfaceAdapters.DTO;

public class EstatisticaPontosResgatadosResponse {
    private int mes;
    private int ano;
    private Long totalPontosResgatados;

    public EstatisticaPontosResgatadosResponse() {}

    public EstatisticaPontosResgatadosResponse(int mes, int ano, Long totalPontosResgatados) {
        this.mes = mes;
        this.ano = ano;
        this.totalPontosResgatados = totalPontosResgatados;
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

    public Long getTotalPontosResgatados() {
        return totalPontosResgatados;
    }

    public void setTotalPontosResgatados(Long totalPontosResgatados) {
        this.totalPontosResgatados = totalPontosResgatados;
    }
}