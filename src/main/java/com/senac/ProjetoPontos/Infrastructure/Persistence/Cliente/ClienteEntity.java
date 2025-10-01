package com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio.ComercioEntity;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Produto.ProdutoEntity;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Usuario.UsuarioEntity;

import jakarta.persistence.*;

@Entity
@Table(name="Cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true)
    private UsuarioEntity usuario;
    @Column
    private int pontos;
    @ManyToMany
    @JoinTable(
        name = "cliente_comercio_favoritos",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "comercio_id")
    )
    private List<ComercioEntity> comercios;
    @ManyToMany
    @JoinTable(
        name = "cliente_produto_favoritos",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<ProdutoEntity> produtos;
    
    protected ClienteEntity() {}

    public ClienteEntity(UUID id, UsuarioEntity usuario, int pontos, List<ComercioEntity> comercios, List<ProdutoEntity> produtos) {
        this.id = id;
        this.usuario = usuario;
        this.pontos = pontos;
        this.comercios = comercios;
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
    public int getPontos() {
        return pontos;
    }
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    public List<ComercioEntity> getComercios() {
        return comercios;
    }
    public void setComercios(List<ComercioEntity> comercios) {
        this.comercios = comercios;
    }
    public List<ProdutoEntity> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<ProdutoEntity> produtos) {
        this.produtos = produtos;
    }

}
