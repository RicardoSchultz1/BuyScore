package com.senac.ProjetoPontos.Domain.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.senac.ProjetoPontos.Domain.Entity.Comercio;

public interface ComercioRepository {

    Comercio save(Comercio comercio);
    Comercio findById(UUID id);
    List<Comercio> findAll();
    boolean existsByUsuarioId(UUID usuarioId);
    void update(Comercio comercio);
    void delete(UUID id);
    Optional<Comercio> findByCnpj(String cnpj);
    Optional<Comercio> findByUsuarioId(UUID usuarioId);
    
    List<Comercio> findTop5BySeguimento(String seguimento);
    List<Comercio> findTop5BySeguimentos(List<String> seguimentos);
    List<Comercio> findTop5FromEachSector();
}
