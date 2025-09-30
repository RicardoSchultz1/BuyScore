package com.senac.ProjetoPontos.Domain.Repository;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;

public interface ClienteRepository {
    
    void save(Cliente cliente);
    Cliente findById(UUID id);
    List<Cliente> findAll();
    void update(Cliente cliente);
    void delete(UUID id);

}
