package com.senac.ProjetoPontos.Infrastructure.Persistence.Endereco;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoJpaRepository extends JpaRepository<EnderecoEntity, UUID> {
    
}
