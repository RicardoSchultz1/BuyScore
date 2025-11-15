package com.senac.ProjetoPontos.Infrastructure.Persistence.Compra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.senac.ProjetoPontos.Domain.Entity.Compra;
import com.senac.ProjetoPontos.Domain.Repository.CompraRepository;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaCompraMensalResponse;

@Repository
public class CompraRepositoryImpl implements CompraRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(CompraRepositoryImpl.class);
    
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

    @Override
    public List<EstatisticaCompraMensalResponse> contarComprasPorMesPorComercio(UUID comercioId) {
        logger.info("Executando query de estatísticas de compras para comercioId: {}", comercioId);
        
        try {
            List<Object[]> resultados = jpaRepository.contarComprasPorMesPorComercio(comercioId);
            logger.info("Query de compras executada com sucesso. Resultados encontrados: {}", resultados.size());
            
            if (resultados.isEmpty()) {
                logger.warn("Nenhuma compra encontrada na query para comercioId: {}", comercioId);
                return List.of(); // Retorna lista vazia
            }
            
            List<EstatisticaCompraMensalResponse> response = resultados.stream()
                    .map(linha -> {
                        try {
                            // Cast seguro usando Number.intValue()
                            int mes = ((Number) linha[0]).intValue();
                            int ano = ((Number) linha[1]).intValue();
                            int quantidade = ((Number) linha[2]).intValue();
                            logger.debug("Processando linha de compra: mes={}, ano={}, quantidade={}", mes, ano, quantidade);
                            return new EstatisticaCompraMensalResponse(mes, ano, quantidade);
                        } catch (Exception e) {
                            logger.error("Erro ao processar linha da query de compras: {}", linha, e);
                            throw new RuntimeException("Erro ao processar resultado da query de compras", e);
                        }
                    })
                    .collect(Collectors.toList());
            
            logger.info("Estatísticas de compras processadas com sucesso. Total de registros: {}", response.size());
            return response;
            
        } catch (Exception e) {
            logger.error("Erro ao executar query de estatísticas de compras para comercioId: {}", comercioId, e);
            throw new RuntimeException("Erro ao buscar estatísticas de compras", e);
        }
    }

    @Override
    public Compra findByCodigo(String codigo) {
        return jpaRepository.findByCodigo(codigo)
                .map(entity -> mapper.map(entity, Compra.class))
                .orElse(null);
    }
}