package com.senac.ProjetoPontos.Infrastructure.Persistence.ClientePontoComercio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.senac.ProjetoPontos.Domain.Repository.ClientePontoComercioRepository;
import com.senac.ProjetoPontos.Domain.Entity.ClientePontoComercio;
import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente.ClienteEntity;
import com.senac.ProjetoPontos.Infrastructure.Persistence.Comercio.ComercioEntity;

import java.util.UUID;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientePontoComercioRepositoryImpl implements ClientePontoComercioRepository {
    
    @Autowired
    private ClientePontoComercioJpaRepository clientePontoComercioJpaRepository;
    
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public Optional<ClientePontoComercio> findByClienteAndComercio(Cliente cliente, Comercio comercio) {
        ClienteEntity clienteEntity = mapper.map(cliente, ClienteEntity.class);
        ComercioEntity comercioEntity = mapper.map(comercio, ComercioEntity.class);
        
        Optional<ClientePontoComercioEntity> entityOpt = clientePontoComercioJpaRepository.findByClienteAndComercio(clienteEntity, comercioEntity);
        return entityOpt.map(entity -> mapper.map(entity, ClientePontoComercio.class));
    }
    
    @Override
    public List<ClientePontoComercio> findByCliente(Cliente cliente) {
        ClienteEntity clienteEntity = mapper.map(cliente, ClienteEntity.class);
        List<ClientePontoComercioEntity> entities = clientePontoComercioJpaRepository.findByCliente(clienteEntity);
        return entities.stream()
            .map(entity -> mapper.map(entity, ClientePontoComercio.class))
            .collect(Collectors.toList());
    }
    
    @Override
    public List<ClientePontoComercio> findByComercio(Comercio comercio) {
        ComercioEntity comercioEntity = mapper.map(comercio, ComercioEntity.class);
        List<ClientePontoComercioEntity> entities = clientePontoComercioJpaRepository.findByComercio(comercioEntity);
        return entities.stream()
            .map(entity -> mapper.map(entity, ClientePontoComercio.class))
            .collect(Collectors.toList());
    }
    
    @Override
    public ClientePontoComercio save(ClientePontoComercio clientePontoComercio) {
        ClientePontoComercioEntity entity = mapper.map(clientePontoComercio, ClientePontoComercioEntity.class);
        ClientePontoComercioEntity savedEntity = clientePontoComercioJpaRepository.save(entity);
        return mapper.map(savedEntity, ClientePontoComercio.class);
    }
    
    @Override
    public void deleteById(UUID id) {
        clientePontoComercioJpaRepository.deleteById(id);
    }
    
    @Override
    public Optional<ClientePontoComercio> findById(UUID id) {
        Optional<ClientePontoComercioEntity> entityOpt = clientePontoComercioJpaRepository.findById(id);
        return entityOpt.map(entity -> mapper.map(entity, ClientePontoComercio.class));
    }
    
    @Override
    public List<ClientePontoComercio> findAll() {
        List<ClientePontoComercioEntity> entities = clientePontoComercioJpaRepository.findAll();
        return entities.stream()
            .map(entity -> mapper.map(entity, ClientePontoComercio.class))
            .collect(Collectors.toList());
    }
}