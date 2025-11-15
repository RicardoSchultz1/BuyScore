package com.senac.ProjetoPontos.Infrastructure.Persistence.ClientePontoComercio;

import jakarta.persistence.*;
import java.util.UUID;

import com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente.ClienteEntity;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio.ComercioEntity;

@Entity
@Table(name = "cliente_ponto_comercio")
public class ClientePontoComercioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comercio_id", nullable = false)
    private ComercioEntity comercio;
    @Column(name = "pontos", nullable = false)
    private Integer pontos;

    public ClientePontoComercioEntity() {}

    public ClientePontoComercioEntity(UUID id, ClienteEntity cliente, ComercioEntity comercio, Integer pontos) {
        this.id = id;
        this.cliente = cliente;
        this.comercio = comercio;
        this.pontos = pontos;
    }

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

    public ComercioEntity getComercio() {
        return comercio;
    }

    public void setComercio(ComercioEntity comercio) {
        this.comercio = comercio;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }
}