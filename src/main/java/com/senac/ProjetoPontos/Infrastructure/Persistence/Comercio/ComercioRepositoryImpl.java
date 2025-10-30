package com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio;

import java.util.Optional;
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
    public boolean existsByUsuarioId(UUID usuarioId) {
        return jpaRepository.existsByUsuario_Id(usuarioId);
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
    
    @Override
    public Optional<Comercio> findByCnpj(String cnpj) {
        return jpaRepository.findByCnpj(cnpj)
                .map(entity -> mapper.map(entity, Comercio.class));
    }

    @Override
    public Optional<Comercio> findByUsuarioId(UUID usuarioId) {
        return jpaRepository.findByUsuario_Id(usuarioId)
                .map(entity -> mapper.map(entity, Comercio.class));
    }

    @Override
    public java.util.List<Comercio> findTop5BySeguimento(String seguimento) {
        java.util.List<ComercioEntity> entities = jpaRepository.findTop5BySeguimentoOrderByVendasDesc(seguimento);
        
        java.util.List<Comercio> result = entities.stream()
                .limit(5)
                .map(entity -> mapper.map(entity, Comercio.class))
                .toList();
                
        return result;
    }

    @Override
    public java.util.List<Comercio> findTop5BySeguimentos(java.util.List<String> seguimentos) {
        return jpaRepository.findTop5BySeguimentoInOrderByVendasDesc(seguimentos).stream()
                .limit(5)
                .map(entity -> mapper.map(entity, Comercio.class))
                .toList();
    }

    @Override
    public java.util.List<Comercio> findTop5FromEachSector() {
        return jpaRepository.findTop5FromEachSector().stream()
                .map(entity -> mapper.map(entity, Comercio.class))
                .toList();
    }
}
