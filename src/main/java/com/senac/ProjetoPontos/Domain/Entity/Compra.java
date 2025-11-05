 package com.senac.ProjetoPontos.Domain.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Compra {
    private UUID id;
    private Cliente cliente;
    private Produto produto;
    private Integer quantidade;
    private Double valorTotal;
    private LocalDateTime dataCompra;
    private String status; // "PENDENTE", "CONFIRMADA", "CANCELADA"

    public Compra() {}

    public Compra(UUID id, Cliente cliente, Produto produto, Integer quantidade, Double valorTotal, LocalDateTime dataCompra, String status) {
        this.id = id;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataCompra = dataCompra;
        this.status = status;
    }

    // Getters e Setters
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                ", dataCompra=" + dataCompra +
                ", status='" + status + '\'' +
                '}';
    }
}