package com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Infrastructure.Persistence.Produto.ProdutoEntity;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Usuario.UsuarioEntity;

import jakarta.persistence.*;

@Entity
@Table(name="Comercio")
public class ComercioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true)
    private UsuarioEntity usuario;
    @Column
    private String nomeComercio;
    @Column
    private String CNPJ;
    @Column
    private String razaoSocial;
    @Column
    private String descricao;
    @Column
    private String seguimento;
    @ManyToOne
    @JoinColumn(name = "id_matriz", referencedColumnName = "id")
    private UsuarioEntity matriz;
    @OneToMany(mappedBy = "comercio")
    private List<ProdutoEntity> produtos;

    protected ComercioEntity() {}

    public ComercioEntity(UUID id, UsuarioEntity usuario, String nomeComercio, String CNPJ, String razaoSocial, String descricao, String seguimento, UsuarioEntity matriz, List<ProdutoEntity> produtos) {
        this.id = id;
        this.usuario = usuario;
        this.nomeComercio = nomeComercio;
        this.CNPJ = CNPJ;
        this.razaoSocial = razaoSocial;
        this.descricao = descricao;
        this.seguimento = seguimento;
        this.matriz = matriz;
        this.produtos = produtos;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UsuarioEntity getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    public String getNomeComercio() {
        return nomeComercio;
    }
    public void setNomeComercio(String nomeComercio) {
        this.nomeComercio = nomeComercio;
    }
    public String getCNPJ() {
        return CNPJ;
    }
    public void setCNPJ(String cNPJ) {
        CNPJ = cNPJ;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getSeguimento() {
        return seguimento;
    }
    public void setSeguimento(String seguimento) {
        this.seguimento = seguimento;
    }
    public UsuarioEntity getMatriz() {
        return matriz;
    }
    public void setMatriz(UsuarioEntity matriz) {
        this.matriz = matriz;
    }
    public List<ProdutoEntity> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<ProdutoEntity> produtos) {
        this.produtos = produtos;
    }

}
