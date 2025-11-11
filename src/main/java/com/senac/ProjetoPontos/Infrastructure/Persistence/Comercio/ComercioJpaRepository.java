package com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComercioJpaRepository extends JpaRepository<ComercioEntity, UUID> {

	boolean existsByUsuario_Id(UUID usuarioId);
	Optional<ComercioEntity> findByCnpj(String cnpj);
	Optional<ComercioEntity> findByUsuario_Id(UUID usuarioId);
	
	// Buscar top 5 comércios por setor ordenados por vendas (decrescente)
	@Query("SELECT c FROM ComercioEntity c WHERE c.seguimento = :seguimento ORDER BY c.vendas DESC")
	List<ComercioEntity> findTop5BySeguimentoOrderByVendasDesc(@Param("seguimento") String seguimento);
	
	// Buscar top 5 comércios de múltiplos setores ordenados por vendas
	@Query("SELECT c FROM ComercioEntity c WHERE c.seguimento IN :seguimentos ORDER BY c.vendas DESC")
	List<ComercioEntity> findTop5BySeguimentoInOrderByVendasDesc(@Param("seguimentos") List<String> seguimentos);
	
	// Consulta nativa alternativa - buscar top 5 por setor específico
	@Query(value = "SELECT * FROM comercio WHERE seguimento = :seguimento ORDER BY vendas DESC LIMIT 5", nativeQuery = true)
	List<ComercioEntity> findTop5BySeguimentoNative(@Param("seguimento") String seguimento);
	
	// Consulta para buscar top 5 de cada um dos 3 setores
	@Query(value = """
		(SELECT * FROM comercio WHERE seguimento = 'restaurante' ORDER BY vendas DESC LIMIT 5)
		UNION ALL
		(SELECT * FROM comercio WHERE seguimento = 'farmacia' ORDER BY vendas DESC LIMIT 5)
		UNION ALL
		(SELECT * FROM comercio WHERE seguimento = 'outros' ORDER BY vendas DESC LIMIT 5)
		ORDER BY vendas DESC
		""", nativeQuery = true)
	List<ComercioEntity> findTop5FromEachSector();

	// Busca por nome (razão social ou nome do usuário) - case insensitive usando Spring JPA
	List<ComercioEntity> findByRazaoSocialContainingIgnoreCaseOrUsuario_NomeContainingIgnoreCase(String razaoSocial, String nomeUsuario);

}
