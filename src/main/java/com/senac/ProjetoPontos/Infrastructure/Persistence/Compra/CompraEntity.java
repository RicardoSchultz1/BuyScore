package com.senac.ProjetoPontos.Infrastructure.Persistence.Compra;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente.ClienteEntity;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Produto.ProdutoEntity;

@Entity
@Table(name = "compra")
public class CompraEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;
    @Column(name = "data_compra", nullable = false)
    private LocalDateTime dataCompra;
    @Column(name = "status", nullable = false)
    private String status;

    public CompraEntity() {}

    public CompraEntity(UUID id, ClienteEntity cliente, ProdutoEntity produto, Integer quantidade, 
                       Double valorTotal, LocalDateTime dataCompra, String status) {
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

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
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
}