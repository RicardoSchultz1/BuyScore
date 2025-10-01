package com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComercioJpaRepository extends JpaRepository<ComercioEntity, UUID> {

}
