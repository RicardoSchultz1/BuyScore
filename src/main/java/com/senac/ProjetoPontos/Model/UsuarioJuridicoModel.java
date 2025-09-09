package com.senac.ProjetoPontos.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="UsuarioJuridico")
public class UsuarioJuridicoModel {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String descricaoLoja;
    @Column(nullable=false, unique = true)
    private String seguimento;
    @Column(nullable=false, unique = true)
	private long cnpj;
    @OneToMany(mappedBy = "usuarioJuridico")
	private List<ProdutoModel> produtos;
    @ManyToOne
	private UsuarioModel usuario;
    @ManyToOne
	private UsuarioModel idMatriz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoLoja() {
        return descricaoLoja;
    }

    public void setDescricaoLoja(String descricaoLoja) {
        this.descricaoLoja = descricaoLoja;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }
    public UsuarioModel getIdMatriz() {
        return idMatriz;
    }

    public void setIdMatriz(UsuarioModel idMatriz) {
        this.idMatriz = idMatriz;
    }
    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
    public long getCnpj() {
        return cnpj;
    }
    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }
    public String getSeguimento() {
        return seguimento;
    }

    public void setSeguimento(String seguimento) {
        this.seguimento = seguimento;
    }

}
