package com.senac.ProjetoPontos.Aplication.UseCase;

import com.senac.ProjetoPontos.Domain.Entity.Endereco;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.Console;
import java.util.Map;

@Service
public class CepUseCase {

	private final RestTemplate restTemplate;

	@Autowired
	public CepUseCase(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Consulta ViaCEP e retorna um Endereco correspondente ao CEP informado.
	 * Lança NaoEncontradoException quando ViaCEP retorna erro ou cep inválido.
	 */
	public Endereco buscarPorCep(String cep) {
		if (cep == null) {
			throw new NaoEncontradoException("CEP nulo");
		}

		// normaliza: remove tudo que não for número
		String cleaned = cep.replaceAll("\\D", "");
		if (cleaned.length() != 8) {
			throw new NaoEncontradoException("CEP inválido: " + cep);
		}

		String url = String.format("https://viacep.com.br/ws/%s/json/", cleaned);
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> resp = restTemplate.getForObject(url, Map.class);
			if (resp == null || resp.containsKey("erro")) {
				throw new NaoEncontradoException(cleaned);
			}

			String respCep = (String) resp.getOrDefault("cep", cleaned);
			String logradouro = (String) resp.getOrDefault("logradouro", null);
			String complemento = (String) resp.getOrDefault("complemento", null);
			String bairro = (String) resp.getOrDefault("bairro", null);
			String cidade = (String) resp.getOrDefault("localidade", null);
			String uf = (String) resp.getOrDefault("uf", null);

            System.console().writer().println(cidade);
            Endereco endereco = new Endereco();
            endereco.setCep(respCep);
            endereco.setCidade(cidade);
            endereco.setBairro(bairro);
            endereco.setLogradouro(logradouro);
            endereco.setComplemento(complemento);
            endereco.setUf(uf);
			return endereco;

		} catch (RestClientException ex) {
			throw new NaoEncontradoException(cep);
		}
	}
}
