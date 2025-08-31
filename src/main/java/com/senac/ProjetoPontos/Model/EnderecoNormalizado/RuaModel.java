package com.senac.ProjetoPontos.Model.EnderecoNormalizado;

import java.util.List;

import com.senac.ProjetoPontos.Model.EnderecoModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Rua")
public class RuaModel {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String nomeRua;
	@Column(nullable=false)
	private long cep;
	@OneToMany(mappedBy = "rua")
	private List<EnderecoModel> enderecos;

	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id = id;
	}	
	public String getNomeRua() {
		return nomeRua;
	}
	public void setNomeRua(String nomeRua) {
		this.nomeRua = nomeRua;
	}
	public List<EnderecoModel> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<EnderecoModel> enderecos) {
		this.enderecos = enderecos;
	}
	public long getCep() {
		return cep;
	}
	public void setCep(long cep) {
		this.cep = cep;
	}

}
