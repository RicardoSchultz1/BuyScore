package com.senac.ProjetoPontos.Domain.Repository;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Domain.Entity.Comercio;

public interface ComercioRepository {

    Comercio save(Comercio comercio);
    Comercio findById(UUID id);
    List<Comercio> findAll();
    boolean existsByUsuarioId(UUID usuarioId);
    void update(Comercio comercio);
    void delete(UUID id);
}
