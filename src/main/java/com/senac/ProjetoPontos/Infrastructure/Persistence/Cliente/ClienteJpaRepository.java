package com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio.ComercioEntity;

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
} 
    
