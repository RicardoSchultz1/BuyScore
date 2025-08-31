package com.senac.ProjetoPontos.Model;

import com.senac.ProjetoPontos.Model.EnderecoNormalizado.BairroModel;
import com.senac.ProjetoPontos.Model.EnderecoNormalizado.CidadeModel;
import com.senac.ProjetoPontos.Model.EnderecoNormalizado.RuaModel;
import com.senac.ProjetoPontos.Model.EnderecoNormalizado.UfModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Endereco")
public class EnderecoModel {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String complemento;
	@Column(nullable=false)
	private int numero;
	@ManyToOne
	private RuaModel rua;
	@ManyToOne
	private CidadeModel cidade;
	@ManyToOne
	private BairroModel bairro;
	@ManyToOne
	private UfModel uf;
	@OneToOne
	private UsuarioModel usuario;

	public int getId() {
		return id;
	}
	public String getComplemento() {
		return complemento;
	}
	public RuaModel getRua() {
		return rua;
	}
	public CidadeModel getCidade() {
		return cidade;
	}
	public BairroModel getBairro() {
		return bairro;
	}
	public UfModel getUf() {
		return uf;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setRua(RuaModel rua) {
		this.rua = rua;
	}
	public void setCidade(CidadeModel cidade) {
		this.cidade = cidade;
	}
	public void setBairro(BairroModel bairro) {
		this.bairro = bairro;
	}
	public void setUf(UfModel uf) {
		this.uf = uf;
	}
}
