package com.senac.ProjetoPontos.Infrastructure.Persistence.Ponto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.senac.ProjetoPontos.Domain.Entity.Ponto;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.PontoRepository;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaMensalResponse;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaPontosResgatadosResponse;

@Repository
public class PontoRepositoryImpl implements PontoRepository {

    private static final Logger logger = LoggerFactory.getLogger(PontoRepositoryImpl.class);
    
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
        logger.info("Executando query de estatísticas para comercioId: {}", comercioId);
        
        try {
            List<Object[]> resultados = jpaRepository.contarClientesPorMesPorComercio(comercioId);
            logger.info("Query executada com sucesso. Resultados encontrados: {}", resultados.size());
            
            if (resultados.isEmpty()) {
                logger.warn("Nenhum resultado encontrado na query para comercioId: {}", comercioId);
                return List.of(); // Retorna lista vazia em vez de null
            }
            
            List<EstatisticaMensalResponse> response = resultados.stream()
                    .map(linha -> {
                        try {
                            // Cast seguro para todos os tipos usando Number
                            int mes = ((Number) linha[0]).intValue();
                            int ano = ((Number) linha[1]).intValue();
                            int quantidade = ((Number) linha[2]).intValue();
                            logger.debug("Processando linha: mes={}, ano={}, quantidade={}", mes, ano, quantidade);
                            return new EstatisticaMensalResponse(mes, ano, quantidade);
                        } catch (Exception e) {
                            logger.error("Erro ao processar linha da query: {}", linha, e);
                            throw new RuntimeException("Erro ao processar resultado da query", e);
                        }
                    })
                    .collect(Collectors.toList());
            
            logger.info("Estatísticas processadas com sucesso. Total de registros: {}", response.size());
            return response;
            
        } catch (Exception e) {
            logger.error("Erro ao executar query de estatísticas para comercioId: {}", comercioId, e);
            throw new RuntimeException("Erro ao buscar estatísticas", e);
        }
    }

    @Override
    public List<EstatisticaPontosResgatadosResponse> somarPontosResgatadosPorMesPorComercio(UUID comercioId) {
        logger.info("Executando query de soma de pontos resgatados para comercioId: {}", comercioId);
        
        try {
            List<Object[]> resultados = jpaRepository.somarPontosResgatadosPorMesPorComercio(comercioId);
            logger.info("Query de soma de pontos executada com sucesso. Resultados encontrados: {}", resultados.size());
            
            if (resultados.isEmpty()) {
                logger.warn("Nenhum resultado encontrado na query de soma de pontos para comercioId: {}", comercioId);
                return List.of(); // Retorna lista vazia em vez de null
            }
            
            List<EstatisticaPontosResgatadosResponse> response = resultados.stream()
                    .map(linha -> {
                        try {
                            // Cast seguro para todos os tipos usando Number
                            int mes = ((Number) linha[0]).intValue();
                            int ano = ((Number) linha[1]).intValue();
                            Long totalPontos = ((Number) linha[2]).longValue();
                            logger.debug("Processando linha soma pontos: mes={}, ano={}, totalPontos={}", mes, ano, totalPontos);
                            return new EstatisticaPontosResgatadosResponse(mes, ano, totalPontos);
                        } catch (Exception e) {
                            logger.error("Erro ao processar linha da query de soma de pontos: {}", linha, e);
                            throw new RuntimeException("Erro ao processar resultado da query de soma de pontos", e);
                        }
                    })
                    .collect(Collectors.toList());
            
            logger.info("Soma de pontos resgatados processada com sucesso. Total de registros: {}", response.size());
            return response;
            
        } catch (Exception e) {
            logger.error("Erro ao executar query de soma de pontos para comercioId: {}", comercioId, e);
            throw new RuntimeException("Erro ao buscar soma de pontos resgatados", e);
        }
    }

}
