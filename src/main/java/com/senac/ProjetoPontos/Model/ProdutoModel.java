package com.senac.ProjetoPontos.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Produto")
public class ProdutoModel {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String nomeProduto;
    @Column(nullable=false)
	private String descricaoProduto;
    @Column(nullable=false)
	private int valor;
    @Column(nullable=false)
	private String fotoProduto;
    @ManyToOne
	private UsuarioJuridicoModel usuarioJuridico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getFotoProduto() {
        return fotoProduto;
    }

    public void setFotoProduto(String fotoProduto) {
        this.fotoProduto = fotoProduto;
    }
    public UsuarioJuridicoModel getUsuarioJuridico() {
        return usuarioJuridico;
    }

    public void setUsuarioJuridico(UsuarioJuridicoModel usuarioJuridico) {
        this.usuarioJuridico = usuarioJuridico;
    }
}
