package com.senac.ProjetoPontos.Infrastructure.Persistence.Compra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.senac.ProjetoPontos.Domain.Entity.Compra;
import com.senac.ProjetoPontos.Domain.Repository.CompraRepository;

@Repository
public class CompraRepositoryImpl implements CompraRepository {
    
    private final CompraJpaRepository jpaRepository;
    private final ModelMapper mapper;

    public CompraRepositoryImpl(CompraJpaRepository jpaRepository, ModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Compra save(Compra compra) {
        CompraEntity entity = mapper.map(compra, CompraEntity.class);
        CompraEntity savedEntity = jpaRepository.save(entity);
        return mapper.map(savedEntity, Compra.class);
    }

    @Override
    public Optional<Compra> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(entity -> mapper.map(entity, Compra.class));
    }

    @Override
    public List<Compra> findAll() {
        return jpaRepository.findAllOrderByDataCompraDesc().stream()
                .map(entity -> mapper.map(entity, Compra.class))
                .toList();
    }

    @Override
    public List<Compra> findByClienteId(UUID clienteId) {
        return jpaRepository.findByClienteId(clienteId).stream()
                .map(entity -> mapper.map(entity, Compra.class))
                .toList();
    }

    @Override
    public List<Compra> findByProdutoId(UUID produtoId) {
        return jpaRepository.findByProdutoId(produtoId).stream()
                .map(entity -> mapper.map(entity, Compra.class))
                .toList();
    }

    @Override
    public List<Compra> findByStatus(String status) {
        return jpaRepository.findByStatus(status).stream()
                .map(entity -> mapper.map(entity, Compra.class))
                .toList();
    }

    @Override
    public void update(Compra compra) {
        CompraEntity entity = mapper.map(compra, CompraEntity.class);
        jpaRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }
}