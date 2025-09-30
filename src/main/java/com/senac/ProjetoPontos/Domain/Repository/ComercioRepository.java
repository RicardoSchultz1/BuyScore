package com.senac.ProjetoPontos.Domain.Repository;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Domain.Entity.Comercio;

public interface ComercioRepository {

    void save(Comercio comercio);
    Comercio findById(UUID id);
    List<Comercio> findAll();
    void update(Comercio comercio);
    void delete(UUID id);
}
