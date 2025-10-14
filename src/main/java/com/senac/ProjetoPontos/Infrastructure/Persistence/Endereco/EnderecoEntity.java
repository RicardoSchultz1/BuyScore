package com.senac.ProjetoPontos.Infrastructure.Persistence.Endereco;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name="Endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
    @Column
    private String cep;
    @Column
	private String logradouro;
    @Column
	private String complemento;
    @Column
	private String bairro;
    @Column
	private String cidade; 
    @Column
    private int numero;
    @Column
	private String uf; 
    
    protected EnderecoEntity() {}

    public EnderecoEntity(UUID id) {
        this.id = id;
    }
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;   
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
}
