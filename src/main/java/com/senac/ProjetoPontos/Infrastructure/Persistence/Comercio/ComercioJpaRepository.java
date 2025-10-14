package com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComercioJpaRepository extends JpaRepository<ComercioEntity, UUID> {

	boolean existsByUsuario_Id(UUID usuarioId);
	java.util.Optional<ComercioEntity> findByCnpj(String cnpj);

}
