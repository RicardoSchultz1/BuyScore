package com.senac.ProjetoPontos.Domain.Repository;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Produto;

public interface ClienteRepository {
    
    Cliente save(Cliente cliente);
    Cliente findById(UUID id);
    List<Cliente> findAll();
    void update(Cliente cliente);
    void delete(UUID id);
    Cliente findByUsuarioId(UUID usuarioId);
    
    void adicionarComercioFavorito(UUID clienteId, UUID comercioId);
    void removerComercioFavorito(UUID clienteId, UUID comercioId);
    List<Comercio> findComerciosFavoritos(UUID clienteId);
    boolean isComercioFavorito(UUID clienteId, UUID comercioId);

    void adicionarProdutoFavorito(UUID clienteId, UUID produtoId);
    void removerProdutoFavorito(UUID clienteId, UUID produtoId);
    List<Produto> findProdutosFavoritos(UUID clienteId);
    boolean isProdutoFavorito(UUID clienteId, UUID produtoId);

}
