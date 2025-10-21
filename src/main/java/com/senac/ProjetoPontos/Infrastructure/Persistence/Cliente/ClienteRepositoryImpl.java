package com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ClienteRepository;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteJpaRepository clienteJpaRepository;
    private final ModelMapper mapper;

    public ClienteRepositoryImpl(ClienteJpaRepository clienteJpaRepository, ModelMapper mapper) {
        this.clienteJpaRepository = clienteJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Cliente findById(UUID id) {
         return clienteJpaRepository.findById(id)
                .map(entity -> mapper.map(entity, Cliente.class))
                .orElseThrow(() -> new NaoEncontradoException(id.toString()));
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = mapper.map(cliente, ClienteEntity.class);
        ClienteEntity saved = clienteJpaRepository.save(entity);
        return mapper.map(saved, Cliente.class);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteJpaRepository.findAll().stream()
                .map(entity -> mapper.map(entity, Cliente.class))
                .toList();
    }

    @Override
    public void update(Cliente cliente) {
        if (clienteJpaRepository.existsById(cliente.getId())) {
            ClienteEntity entity = mapper.map(cliente, ClienteEntity.class);
            clienteJpaRepository.save(entity);
        }
    }

    @Override
    public void delete(UUID id) {
        clienteJpaRepository.deleteById(id);
    }

}
