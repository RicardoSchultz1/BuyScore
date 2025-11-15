package com.senac.ProjetoPontos.Domain.Repository;

import com.senac.ProjetoPontos.Domain.Entity.Compra;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaCompraMensalResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompraRepository {
    Compra save(Compra compra);
    Optional<Compra> findById(UUID id);
    List<Compra> findAll();
    List<Compra> findByClienteId(UUID clienteId);
    List<Compra> findByProdutoId(UUID produtoId);
    List<Compra> findByStatus(String status);
    void update(Compra compra);
    void delete(UUID id);
    List<EstatisticaCompraMensalResponse> contarComprasPorMesPorComercio(UUID comercioId);
    Compra findByCodigo(String codigo);
}