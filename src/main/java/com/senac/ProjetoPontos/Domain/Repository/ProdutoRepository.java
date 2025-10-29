package com.senac.ProjetoPontos.Domain.Repository;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Domain.Entity.Produto;

public interface ProdutoRepository {

    Produto findById(UUID id);
    List<Produto> findAll();
    Produto save(Produto produto);
    void update(Produto produto);
    void delete(UUID id);
    List<Produto> findByComercioId(UUID comercioId);
}
