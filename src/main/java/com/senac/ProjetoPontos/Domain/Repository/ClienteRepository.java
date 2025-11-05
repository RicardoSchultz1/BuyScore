package com.senac.ProjetoPontos.Domain.Repository;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;

public interface ClienteRepository {
    
    Cliente save(Cliente cliente);
    Cliente findById(UUID id);
    List<Cliente> findAll();
    void update(Cliente cliente);
    void delete(UUID id);
    Cliente findByUsuarioId(UUID usuarioId);
    
    // Métodos para gerenciar comércios favoritos
    void adicionarComercioFavorito(UUID clienteId, UUID comercioId);
    void removerComercioFavorito(UUID clienteId, UUID comercioId);
    List<Comercio> findComerciosFavoritos(UUID clienteId);
    boolean isComercioFavorito(UUID clienteId, UUID comercioId);

}
