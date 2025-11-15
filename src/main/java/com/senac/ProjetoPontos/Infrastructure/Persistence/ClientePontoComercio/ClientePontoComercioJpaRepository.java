package com.senac.ProjetoPontos.Infrastructure.Persistence.ClientePontoComercio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente.ClienteEntity;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio.ComercioEntity;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

@Repository
public interface ClientePontoComercioJpaRepository extends JpaRepository<ClientePontoComercioEntity, UUID> {
    
    Optional<ClientePontoComercioEntity> findByClienteAndComercio(ClienteEntity cliente, ComercioEntity comercio);
    
    List<ClientePontoComercioEntity> findByCliente(ClienteEntity cliente);
    
    List<ClientePontoComercioEntity> findByComercio(ComercioEntity comercio);
}