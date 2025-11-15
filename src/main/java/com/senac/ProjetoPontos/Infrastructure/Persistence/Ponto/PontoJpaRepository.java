package com.senac.ProjetoPontos.Infrastructure.Persistence.Ponto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PontoJpaRepository extends JpaRepository<PontoEntity, UUID> {

    Optional<PontoEntity> getByCodigo(String codigo);
    
    // Query para contar clientes únicos que resgataram pontos por mês em um comércio
    @Query(value = """
        SELECT 
            MONTH(p.data) as mes,
            YEAR(p.data) as ano,
            COUNT(DISTINCT p.cliente_id) as quantidadeClientes
        FROM ponto p
        WHERE p.comercio_id = :comercioId
        GROUP BY YEAR(p.data), MONTH(p.data)
        ORDER BY YEAR(p.data) DESC, MONTH(p.data) DESC
        """, nativeQuery = true)
    List<Object[]> contarClientesPorMesPorComercio(@Param("comercioId") UUID comercioId);
    
    // Query para somar total de pontos resgatados por mês em um comércio
    @Query(value = """
        SELECT 
            MONTH(p.data) as mes,
            YEAR(p.data) as ano,
            SUM(p.pontos) as totalPontosResgatados
        FROM ponto p
        WHERE p.comercio_id = :comercioId 
        AND p.cliente_id IS NOT NULL
        GROUP BY YEAR(p.data), MONTH(p.data)
        ORDER BY YEAR(p.data) DESC, MONTH(p.data) DESC
        """, nativeQuery = true)
    List<Object[]> somarPontosResgatadosPorMesPorComercio(@Param("comercioId") UUID comercioId);
}
