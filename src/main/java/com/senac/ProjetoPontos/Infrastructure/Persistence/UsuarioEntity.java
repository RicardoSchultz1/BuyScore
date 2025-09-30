package com.senac.ProjetoPontos.Infrastructure.Persistence;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;


@Entity
@Table(name="Usuario")
public class UsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable= false, unique=true)
	private String email;
	@Column(nullable= false)
	private String senha;
	//@OneToOne(mappedBy = "usuario")
	//private EnderecoEntity endereco;
	@Column(nullable= false)
	private int perfilUsuario;
	@Column(nullable= false)
	private String fotoUsuario;

    // Construtor padrão exigido pelo JPA
    protected UsuarioEntity() {}

    public UsuarioEntity(UUID id, String nome, String email, String senha, int perfilUsuario, String fotoUsuario) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.perfilUsuario = perfilUsuario;
		this.fotoUsuario = fotoUsuario;
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
	/*public EnderecoModel getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}
    */
}