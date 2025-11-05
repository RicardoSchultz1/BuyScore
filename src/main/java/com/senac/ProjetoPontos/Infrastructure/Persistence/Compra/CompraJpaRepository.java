package com.senac.ProjetoPontos.Infrastructure.Persistence.Compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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
}