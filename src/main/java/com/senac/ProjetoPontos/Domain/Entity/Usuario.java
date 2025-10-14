package com.senac.ProjetoPontos.Domain.Entity;

import java.util.UUID;

public class Usuario {
    
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private int perfilUsuario;
    private String fotoUsuario;
    private Endereco endereco;

    public Usuario(UUID id, String nome, String email, String senha, int perfilUsuario, String fotoUsuario, Endereco endereco) {

        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email vazio ou inválido");
        }
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nulo ou vazio");
        }
        if (senha.length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
        }
        if (perfilUsuario < 1 || perfilUsuario > 3 || perfilUsuario == 0) {
            throw new IllegalArgumentException("Perfil de usuário inválido");
        }
        this.id = id;
        this.endereco = endereco;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfilUsuario = perfilUsuario;
        this.fotoUsuario = fotoUsuario;
    }
    
    protected Usuario() { }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    public int getPerfilUsuario() {
        return perfilUsuario;
    }
    public String getFotoUsuario() {
        return fotoUsuario;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }   
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setPerfilUsuario(int perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }
    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
