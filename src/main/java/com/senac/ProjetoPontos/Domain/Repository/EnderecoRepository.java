package com.senac.ProjetoPontos.Domain.Repository;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Domain.Entity.Endereco;

public interface EnderecoRepository {

    Endereco save(Endereco endereco);
    Endereco findById(UUID id);
    List<Endereco> findAll();
    void update(Endereco endereco);
    void delete(UUID id);
    
}
