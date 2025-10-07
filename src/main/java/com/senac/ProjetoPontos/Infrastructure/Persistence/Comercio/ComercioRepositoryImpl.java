package com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ComercioRepository;

@Repository
public class ComercioRepositoryImpl implements ComercioRepository {
    
    private final ComercioJpaRepository jpaRepository;
    private final ModelMapper mapper;

    public ComercioRepositoryImpl(ComercioJpaRepository jpaRepository, ModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Comercio findById(UUID id) {
        return jpaRepository.findById(id)
                .map(entity -> mapper.map(entity, Comercio.class))
                .orElseThrow(() -> new NaoEncontradoException(id.toString()));
    }

    @Override
    public Comercio save(Comercio comercio) {
        ComercioEntity entity = mapper.map(comercio, ComercioEntity.class);
        ComercioEntity saved = jpaRepository.save(entity);
        return mapper.map(saved, Comercio.class);
        
    }

    @Override
    public java.util.List<Comercio> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> mapper.map(entity, Comercio.class))
                .toList();
    }

    @Override
    public void update(Comercio comercio) {
        if (jpaRepository.existsById(comercio.getId())) {
            ComercioEntity entity = mapper.map(comercio, ComercioEntity.class);
            jpaRepository.save(entity);
        }
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    } 
}
