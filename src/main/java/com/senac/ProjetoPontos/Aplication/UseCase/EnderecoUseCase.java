package com.senac.ProjetoPontos.Aplication.UseCase;

import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Endereco;
import com.senac.ProjetoPontos.Domain.Repository.EnderecoRepository;

@Service
public class EnderecoUseCase {
    
    private final EnderecoRepository enderecoRepository;

    public EnderecoUseCase(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco salvarEnderecoEntity(String cep, String logradouro, String complemento, String bairro, String cidade, int numero, String uf) {
        Endereco endereco = new Endereco(null, cep, logradouro, complemento, bairro, cidade, numero, uf);
        return enderecoRepository.save(endereco);
    }

    public Endereco salvarEnderecoRepository(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
    public Endereco buscarEnderecoRepository(java.util.UUID id) {
        return enderecoRepository.findById(id); 
    }
    public void deletarEnderecoRepository(java.util.UUID id) {
        enderecoRepository.delete(id);
    }
    public void atualizarEnderecoRepository(Endereco endereco) {
        enderecoRepository.update(endereco);
    }
    public java.util.List<Endereco> listarEnderecosRepository() {
        return enderecoRepository.findAll();
    }
}
