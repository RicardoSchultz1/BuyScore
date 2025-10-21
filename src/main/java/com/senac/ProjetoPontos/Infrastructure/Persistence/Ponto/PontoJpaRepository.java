package com.senac.ProjetoPontos.Infrastructure.Persistence.Ponto;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoJpaRepository extends JpaRepository<PontoEntity, UUID> {
    
}
