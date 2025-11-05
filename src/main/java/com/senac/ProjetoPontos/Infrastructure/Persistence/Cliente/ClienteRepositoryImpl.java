package com.senac.ProjetoPontos.Infrastructure.Persistence.Cliente;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Produto;
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

    @Override
    public Cliente findByUsuarioId(UUID usuarioId) {
        return clienteJpaRepository.findByUsuarioId(usuarioId)
                .map(entity -> mapper.map(entity, Cliente.class))
                .orElseThrow(() -> new NaoEncontradoException(usuarioId.toString()));
    }

    @Override
    @Transactional
    public void adicionarComercioFavorito(UUID clienteId, UUID comercioId) {
        if (!clienteJpaRepository.existsById(clienteId)) {
            throw new NaoEncontradoException("Cliente não encontrado: " + clienteId);
        }
        if (!clienteJpaRepository.existsComercioFavorito(clienteId, comercioId)) {
            clienteJpaRepository.adicionarComercioFavorito(clienteId, comercioId);
        }
    }

    @Override
    @Transactional
    public void removerComercioFavorito(UUID clienteId, UUID comercioId) {
        if (!clienteJpaRepository.existsById(clienteId)) {
            throw new NaoEncontradoException("Cliente não encontrado: " + clienteId);
        }
        clienteJpaRepository.removerComercioFavorito(clienteId, comercioId);
    }

    @Override
    public List<Comercio> findComerciosFavoritos(UUID clienteId) {
        return clienteJpaRepository.findComerciosFavoritosByClienteId(clienteId).stream()
                .map(entity -> mapper.map(entity, Comercio.class))
                .toList();
    }

    @Override
    public boolean isComercioFavorito(UUID clienteId, UUID comercioId) {
        return clienteJpaRepository.existsComercioFavorito(clienteId, comercioId);
    }

    @Override
    @Transactional
    public void adicionarProdutoFavorito(UUID clienteId, UUID produtoId) {
        if (!clienteJpaRepository.existsById(clienteId)) {
            throw new NaoEncontradoException("Cliente não encontrado: " + clienteId);
        }
        if (!clienteJpaRepository.existsProdutoFavorito(clienteId, produtoId)) {
            clienteJpaRepository.adicionarProdutoFavorito(clienteId, produtoId);
        }
    }

    @Override
    @Transactional
    public void removerProdutoFavorito(UUID clienteId, UUID produtoId) {
        if (!clienteJpaRepository.existsById(clienteId)) {
            throw new NaoEncontradoException("Cliente não encontrado: " + clienteId);
        }
        clienteJpaRepository.removerProdutoFavorito(clienteId, produtoId);
    }

    @Override
    public List<Produto> findProdutosFavoritos(UUID clienteId) {
        return clienteJpaRepository.findProdutosFavoritosByClienteId(clienteId).stream()
                .map(entity -> mapper.map(entity, Produto.class))
                .toList();
    }

    @Override
    public boolean isProdutoFavorito(UUID clienteId, UUID produtoId) {
        return clienteJpaRepository.existsProdutoFavorito(clienteId, produtoId);
    }
}
