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
@Table(name="Cidade")
public class CidadeModel {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String nomeCidade;
    @OneToMany(mappedBy = "cidade")
	private List<EnderecoModel> enderecos;

	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id = id;
	}	
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
    public List<EnderecoModel> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<EnderecoModel> enderecos) {
        this.enderecos = enderecos;
    }

}
