package com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, UUID> {

    
} 
    
