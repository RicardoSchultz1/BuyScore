package com.senac.ProjetoPontos.Infrastructure.Persistence.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import com.senac.ProjetoPontos.Domain.Entity.Produto;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ProdutoRepository;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoJpaRepository produtoJpaRepository;
    private final ModelMapper mapper;

    public ProdutoRepositoryImpl(ProdutoJpaRepository produtoJpaRepository, ModelMapper mapper) {
        this.produtoJpaRepository = produtoJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Produto findById(UUID id) {
        return produtoJpaRepository.findById(id)
                .map(entity -> mapper.map(entity, Produto.class))
                .orElseThrow(() -> new NaoEncontradoException(id.toString()));
    }

    @Override
    public List<Produto> findAll() {
        List<ProdutoEntity> entities = produtoJpaRepository.findAll();
        return entities.stream()
                .map(entity -> mapper.map(entity, Produto.class))
                .toList();
    }

    @Override
    public Produto save(Produto produto) {
        ProdutoEntity entity = mapper.map(produto, ProdutoEntity.class);
        ProdutoEntity savedEntity = produtoJpaRepository.save(entity);
        return mapper.map(savedEntity, Produto.class);
    }

    @Override
    public void update(Produto produto) {
        if (produtoJpaRepository.existsById(produto.getId())) {
            ProdutoEntity entity = mapper.map(produto, ProdutoEntity.class);
            produtoJpaRepository.save(entity);
        }
    }

    @Override
    public void delete(UUID id) {
        produtoJpaRepository.deleteById(id);
    }

    @Override
    public List<Produto> findByComercioId(UUID comercioId) {
        List<ProdutoEntity> entities = produtoJpaRepository.findByComercioId(comercioId);
        return entities.stream()
                .map(entity -> mapper.map(entity, Produto.class))
                .toList();
    }

}