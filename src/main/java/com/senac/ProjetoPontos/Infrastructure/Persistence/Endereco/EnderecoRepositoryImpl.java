package com.senac.ProjetoPontos.Infrastructure.Persistence.Endereco;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.senac.ProjetoPontos.Domain.Entity.Endereco;
import com.senac.ProjetoPontos.Domain.Repository.EnderecoRepository;

@Repository
public class EnderecoRepositoryImpl implements EnderecoRepository {

    private final EnderecoJpaRepository jpaRepository;
    private final ModelMapper mapper;

    public EnderecoRepositoryImpl(EnderecoJpaRepository jpaRepository, ModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Endereco save(Endereco Endereco) {
        EnderecoEntity entity = mapper.map(Endereco, EnderecoEntity.class);
        EnderecoEntity saved = jpaRepository.save(entity);
        return mapper.map(saved, Endereco.class);
        
    }
    @Override
    public Endereco findById(UUID id) {
        return jpaRepository.findById(id).map(entity -> mapper.map(entity, Endereco.class)).orElse(null);
    }
    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }
    @Override
    public List<Endereco> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> mapper.map(entity, Endereco.class))
                .toList();
    }
    @Override
    public void update(Endereco endereco) {
        if (jpaRepository.existsById(endereco.getId())) {
            jpaRepository.save(mapper.map(endereco, EnderecoEntity.class));
        }
    }
    
}
