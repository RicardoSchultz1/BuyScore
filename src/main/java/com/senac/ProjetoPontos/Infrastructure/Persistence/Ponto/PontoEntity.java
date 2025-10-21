package com.senac.ProjetoPontos.Infrastructure.Persistence.Ponto;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.*;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio.ComercioEntity;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente.ClienteEntity;

@Entity
@Table(name="ponto")
public class PontoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String codigo;
    @Column
    private int pontos;
    @Column
    private Date data;
    @ManyToOne
    @JoinColumn(name = "comercio_id")
    private ComercioEntity comercio;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    protected PontoEntity() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public ComercioEntity getComercio() {
        return comercio;
    }

    public void setComercio(ComercioEntity comercio) {
        this.comercio = comercio;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    public Date getDate() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    
}
