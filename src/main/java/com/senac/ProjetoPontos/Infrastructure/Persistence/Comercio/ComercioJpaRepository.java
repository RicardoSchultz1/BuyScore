package com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComercioJpaRepository extends JpaRepository<ComercioEntity, UUID> {

	boolean existsByUsuario_Id(UUID usuarioId);
	Optional<ComercioEntity> findByCnpj(String cnpj);
	Optional<ComercioEntity> findByUsuario_Id(UUID usuarioId);

}
