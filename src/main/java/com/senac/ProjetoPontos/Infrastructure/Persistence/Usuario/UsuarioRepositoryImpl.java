package com.senac.ProjetoPontos.Infrastructure.Persistence.Usuario;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.UsuarioRepository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository jpaRepository;
    private final ModelMapper mapper;

    public UsuarioRepositoryImpl(UsuarioJpaRepository jpaRepository, ModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Usuario findById(UUID id) {
        return jpaRepository.findById(id)
                .map(entity -> mapper.map(entity, Usuario.class))
                .orElseThrow(() -> new NaoEncontradoException(id.toString()));
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = mapper.map(usuario, UsuarioEntity.class);
        UsuarioEntity saved = jpaRepository.save(entity);
        return mapper.map(saved, Usuario.class);
        
    }

    @Override
    public java.util.List<Usuario> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> mapper.map(entity, Usuario.class))
                .toList();
    }

    @Override
    public Usuario findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(entity -> mapper.map(entity, Usuario.class))
                .orElse(null);
    }

    @Override
    public void update(Usuario usuario) {
        if (jpaRepository.existsById(usuario.getId())) {
            UsuarioEntity entity = mapper.map(usuario, UsuarioEntity.class);
            jpaRepository.save(entity);
        }
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    } 

}
