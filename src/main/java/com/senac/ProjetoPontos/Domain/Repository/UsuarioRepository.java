package com.senac.ProjetoPontos.Domain.Repository;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Domain.Entity.Usuario;

public interface UsuarioRepository {

    Usuario save(Usuario usuario);
    Usuario findById(UUID id);
    List<Usuario> findAll();
    void update(Usuario usuario);
    void delete(UUID id);
    
}
