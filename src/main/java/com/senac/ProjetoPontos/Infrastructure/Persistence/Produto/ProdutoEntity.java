package com.senac.ProjetoPontos.Infrastructure.Persistence.Produto;

import java.util.UUID;

import com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio.ComercioEntity;

import jakarta.persistence.*;

@Entity
@Table(name="Produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable=false)
	private String nome;
    @Column(nullable=false)
	private String descricao;
    @Column(nullable=false)
	private int valor;
    @Column(nullable=false)
	private String fotoProduto;
    @ManyToOne
    @JoinColumn(name = "id_comercio", referencedColumnName = "id")
	private ComercioEntity comercio;


    protected ProdutoEntity() {}

    public ProdutoEntity(UUID id, String nome, String descricao, int valor, String fotoProduto, ComercioEntity comercio) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.fotoProduto = fotoProduto;
        this.comercio = comercio;
        //this.resgate = resgate;
    }
  
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    public ComercioEntity getId_comercio() {
        return comercio;
    }
    public void setId_comercio(ComercioEntity comercio) {
        this.comercio = comercio;
    }
    // public List<ResgateModel> getResgate() {
    //     return resgate;
    // }
    // public void setResgate(List<ResgateModel> resgate) {
    //     this.resgate = resgate;
    // }
}
