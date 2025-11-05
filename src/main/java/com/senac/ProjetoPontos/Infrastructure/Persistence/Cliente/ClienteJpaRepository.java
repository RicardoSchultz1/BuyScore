package com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio.ComercioEntity;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Produto.ProdutoEntity;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, UUID> {

    Optional<ClienteEntity> findByUsuarioId(UUID usuarioId);
    
    @Query("SELECT c.comercios FROM ClienteEntity c WHERE c.id = :clienteId")
    List<ComercioEntity> findComerciosFavoritosByClienteId(@Param("clienteId") UUID clienteId);
    
    @Query("SELECT CASE WHEN COUNT(cf) > 0 THEN true ELSE false END " +
           "FROM ClienteEntity c JOIN c.comercios cf " +
           "WHERE c.id = :clienteId AND cf.id = :comercioId")
    boolean existsComercioFavorito(@Param("clienteId") UUID clienteId, @Param("comercioId") UUID comercioId);
    
    @Modifying
    @Query(value = "INSERT INTO cliente_comercio_favoritos (cliente_id, comercio_id) VALUES (:clienteId, :comercioId)", 
           nativeQuery = true)
    void adicionarComercioFavorito(@Param("clienteId") UUID clienteId, @Param("comercioId") UUID comercioId);
    
    @Modifying
    @Query(value = "DELETE FROM cliente_comercio_favoritos WHERE cliente_id = :clienteId AND comercio_id = :comercioId", 
           nativeQuery = true)
    void removerComercioFavorito(@Param("clienteId") UUID clienteId, @Param("comercioId") UUID comercioId);

    @Query("SELECT c.produtos FROM ClienteEntity c WHERE c.id = :clienteId")
    List<ProdutoEntity> findProdutosFavoritosByClienteId(@Param("clienteId") UUID clienteId);
    
    @Query("SELECT CASE WHEN COUNT(pf) > 0 THEN true ELSE false END " +
           "FROM ClienteEntity c JOIN c.produtos pf " +
           "WHERE c.id = :clienteId AND pf.id = :produtoId")
    boolean existsProdutoFavorito(@Param("clienteId") UUID clienteId, @Param("produtoId") UUID produtoId);
    
    @Modifying
    @Query(value = "INSERT INTO cliente_produto_favoritos (cliente_id, produto_id) VALUES (:clienteId, :produtoId)", 
           nativeQuery = true)
    void adicionarProdutoFavorito(@Param("clienteId") UUID clienteId, @Param("produtoId") UUID produtoId);
    
    @Modifying
    @Query(value = "DELETE FROM cliente_produto_favoritos WHERE cliente_id = :clienteId AND produto_id = :produtoId", 
           nativeQuery = true)
    void removerProdutoFavorito(@Param("clienteId") UUID clienteId, @Param("produtoId") UUID produtoId);
} 
    
