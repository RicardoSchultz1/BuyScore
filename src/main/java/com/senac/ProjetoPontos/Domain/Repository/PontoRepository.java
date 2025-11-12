package com.senac.ProjetoPontos.Domain.Repository;

import java.util.List;
import java.util.UUID;

import com.senac.ProjetoPontos.Domain.Entity.Ponto;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaMensalResponse;

public interface PontoRepository {

    Ponto save(Ponto ponto);
    Ponto findById(UUID id);
    List<Ponto> findAll();
    void update(Ponto ponto);
    void delete(UUID id);
    Ponto getByCodigo(String codigo);
    
    // Estatísticas: clientes únicos que resgataram pontos por mês em um comércio
    List<EstatisticaMensalResponse> contarClientesPorMesPorComercio(UUID comercioId);
}
