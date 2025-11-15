package com.senac.ProjetoPontos.Domain.Repository;

import com.senac.ProjetoPontos.Domain.Entity.ClientePontoComercio;
import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

public interface ClientePontoComercioRepository {
    
    Optional<ClientePontoComercio> findByClienteAndComercio(Cliente cliente, Comercio comercio);
    
    List<ClientePontoComercio> findByCliente(Cliente cliente);
    
    List<ClientePontoComercio> findByComercio(Comercio comercio);
    
    ClientePontoComercio save(ClientePontoComercio clientePontoComercio);
    
    void deleteById(UUID id);
    
    Optional<ClientePontoComercio> findById(UUID id);
    
    List<ClientePontoComercio> findAll();
}