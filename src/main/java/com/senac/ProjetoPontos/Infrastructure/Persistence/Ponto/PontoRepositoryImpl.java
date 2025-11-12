package com.senac.ProjetoPontos.Infrastructure.Persistence.Ponto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.senac.ProjetoPontos.Domain.Entity.Ponto;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.PontoRepository;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaMensalResponse;

@Repository
public class PontoRepositoryImpl implements PontoRepository {

    private final PontoJpaRepository jpaRepository;
    private final ModelMapper mapper;

    public PontoRepositoryImpl(PontoJpaRepository jpaRepository, ModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Ponto findById(UUID id) {
        return jpaRepository.findById(id)
                .map(entity -> mapper.map(entity, Ponto.class))
                .orElseThrow(() -> new NaoEncontradoException(id.toString()));
    }

    @Override
    public Ponto save(Ponto ponto) {
        PontoEntity entity = mapper.map(ponto, PontoEntity.class);
        PontoEntity saved = jpaRepository.save(entity);
        return mapper.map(saved, Ponto.class);
        
    }

    @Override
    public java.util.List<Ponto> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> mapper.map(entity, Ponto.class))
                .toList();
    }

    @Override
    public void update(Ponto ponto) {
        if (jpaRepository.existsById(ponto.getId())) {
            PontoEntity entity = mapper.map(ponto, PontoEntity.class);
            jpaRepository.save(entity);
        }
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    } 

    @Override
    public Ponto getByCodigo(String codigo) {
        return jpaRepository.getByCodigo(codigo)
                .map(entity -> mapper.map(entity, Ponto.class))
                .orElseThrow(() -> new NaoEncontradoException(codigo));
    }

    @Override
    public List<EstatisticaMensalResponse> contarClientesPorMesPorComercio(UUID comercioId) {
        List<Object[]> resultados = jpaRepository.contarClientesPorMesPorComercio(comercioId);
        
        return resultados.stream()
                .map(linha -> {
                    int mes = (Integer) linha[0];
                    int ano = (Integer) linha[1];
                    int quantidade = ((Number) linha[2]).intValue();
                    return new EstatisticaMensalResponse(mes, ano, quantidade);
                })
                .collect(Collectors.toList());
    }

}
