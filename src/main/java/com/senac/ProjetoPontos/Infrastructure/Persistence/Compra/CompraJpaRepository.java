package com.senac.ProjetoPontos.Infrastructure.Persistence.Compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompraJpaRepository extends JpaRepository<CompraEntity, UUID> {
    
    @Query("SELECT c FROM CompraEntity c WHERE c.cliente.id = :clienteId")
    List<CompraEntity> findByClienteId(@Param("clienteId") UUID clienteId);
    
    @Query("SELECT c FROM CompraEntity c WHERE c.produto.id = :produtoId")
    List<CompraEntity> findByProdutoId(@Param("produtoId") UUID produtoId);
    
    @Query("SELECT c FROM CompraEntity c WHERE c.status = :status")
    List<CompraEntity> findByStatus(@Param("status") String status);
    
    @Query("SELECT c FROM CompraEntity c ORDER BY c.dataCompra DESC")
    List<CompraEntity> findAllOrderByDataCompraDesc();
    
    // Query para contar compras por mês em um comércio específico
    @Query(value = """
        SELECT 
            MONTH(c.data_compra) as mes,
            YEAR(c.data_compra) as ano,
            COUNT(c.id) as quantidadeCompras
        FROM compra c
        INNER JOIN produto p ON p.id = c.produto_id
        WHERE p.id_comercio = :comercioId
        GROUP BY YEAR(c.data_compra), MONTH(c.data_compra)
        ORDER BY YEAR(c.data_compra) DESC, MONTH(c.data_compra) DESC
        """, nativeQuery = true)
    List<Object[]> contarComprasPorMesPorComercio(@Param("comercioId") UUID comercioId);
    
    @Query("SELECT c FROM CompraEntity c WHERE c.codigo = :codigo")
    Optional<CompraEntity> findByCodigo(@Param("codigo") String codigo);
}