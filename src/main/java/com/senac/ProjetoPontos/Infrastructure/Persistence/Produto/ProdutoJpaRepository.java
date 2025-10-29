package com.senac.ProjetoPontos.Infrastructure.Persistence.Produto;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, UUID> {

}
