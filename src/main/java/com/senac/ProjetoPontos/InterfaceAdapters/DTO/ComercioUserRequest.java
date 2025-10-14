package com.senac.ProjetoPontos.InterfaceAdapters.DTO;

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
    private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String cidade; 
    private int numero;
	private String uf; 

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
