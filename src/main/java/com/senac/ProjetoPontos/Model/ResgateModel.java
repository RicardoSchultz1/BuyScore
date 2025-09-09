package com.senac.ProjetoPontos.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Resgate")
public class ResgateModel {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private int quantidadeResgatada;
    @Column(nullable=false)
    private Date dataResgate;
    @Column(nullable=false)
    private double valorUnitario;
    @ManyToOne
	private ProdutoModel produto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeResgatada() {
        return quantidadeResgatada;
    }

    public void setQuantidadeResgatada(int quantidadeResgatada) {
        this.quantidadeResgatada = quantidadeResgatada;
    }

    public Date getDataResgate() {
        return dataResgate;
    }

    public void setDataResgate(Date dataResgate) {
        this.dataResgate = dataResgate;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }
}