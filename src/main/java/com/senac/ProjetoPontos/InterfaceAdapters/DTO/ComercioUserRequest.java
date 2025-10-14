package com.senac.ProjetoPontos.InterfaceAdapters.DTO;

import java.util.UUID;

public class ComercioUserRequest {

    private String cnpj;
    private String razaoSocial;
    private String descricao;
    private String seguimento;
    private String matrizId;
    private String nome;
    private String email;
    private String senha;
    private int perfilUsuario;
    private String fotoUsuario;
    private UUID idEndereco;

    public ComercioUserRequest() { }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
    public String getMatrizId() {
        return matrizId;
    }
    public void setMatrizId(String matrizId) {
        this.matrizId = matrizId;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getPerfilUsuario() {
        return perfilUsuario;
    }
    public void setPerfilUsuario(int perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }
    public String getFotoUsuario() {
        return fotoUsuario;
    }
    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
    public UUID getIdEndereco() {
        return idEndereco;
    }
    public void setIdEndereco(UUID idEndereco) {
        this.idEndereco = idEndereco;
    }

}
