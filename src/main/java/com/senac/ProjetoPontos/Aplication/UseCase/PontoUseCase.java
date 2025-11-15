package com.senac.ProjetoPontos.Aplication.UseCase;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.ClientePontoComercio;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ClienteRepository;
import com.senac.ProjetoPontos.Domain.Repository.ClientePontoComercioRepository;
import com.senac.ProjetoPontos.Domain.Repository.ComercioRepository;

@Service
public class PontoUseCase {
    
    private final ClienteRepository clienteRepository;
    private final ComercioRepository comercioRepository;
    private final ClientePontoComercioRepository clientePontoComercioRepository;

    public PontoUseCase(ClienteRepository clienteRepository, ComercioRepository comercioRepository, 
                       ClientePontoComercioRepository clientePontoComercioRepository) {
        this.clienteRepository = clienteRepository;
        this.comercioRepository = comercioRepository;
        this.clientePontoComercioRepository = clientePontoComercioRepository;
    }

    public void adicionarPontos(UUID clienteId, UUID comercioId, int pontos) {
        Cliente cliente = clienteRepository.findById(clienteId);
        if (cliente == null) {
            throw new NaoEncontradoException("Cliente não encontrado: " + clienteId);
        }

        Comercio comercio = comercioRepository.findById(comercioId);
        if (comercio == null) {
            throw new NaoEncontradoException("Comércio não encontrado: " + comercioId);
        }

        ClientePontoComercio pontosExistente = clientePontoComercioRepository
            .findByClienteAndComercio(cliente, comercio)
            .orElse(null);

        if (pontosExistente == null) {
            // Criar novo relacionamento
            pontosExistente = new ClientePontoComercio(null, cliente, comercio, pontos);
        } else {
            // Atualizar existente
            pontosExistente.setPontos(pontosExistente.getPontos() + pontos);
        }

        clientePontoComercioRepository.save(pontosExistente);
    }

    public void definirPontos(UUID clienteId, UUID comercioId, int pontos) {
        Cliente cliente = clienteRepository.findById(clienteId);
        if (cliente == null) {
            throw new NaoEncontradoException("Cliente não encontrado: " + clienteId);
        }

        Comercio comercio = comercioRepository.findById(comercioId);
        if (comercio == null) {
            throw new NaoEncontradoException("Comércio não encontrado: " + comercioId);
        }

        ClientePontoComercio pontosExistente = clientePontoComercioRepository
            .findByClienteAndComercio(cliente, comercio)
            .orElse(null);

        if (pontosExistente == null) {
            // Criar novo relacionamento
            pontosExistente = new ClientePontoComercio(null, cliente, comercio, pontos);
        } else {
            // Definir novo valor
            pontosExistente.setPontos(pontos);
        }

        clientePontoComercioRepository.save(pontosExistente);
    }

    public int obterPontos(UUID clienteId, UUID comercioId) {
        Cliente cliente = clienteRepository.findById(clienteId);
        if (cliente == null) {
            throw new NaoEncontradoException("Cliente não encontrado: " + clienteId);
        }

        Comercio comercio = comercioRepository.findById(comercioId);
        if (comercio == null) {
            throw new NaoEncontradoException("Comércio não encontrado: " + comercioId);
        }

        ClientePontoComercio pontos = clientePontoComercioRepository
            .findByClienteAndComercio(cliente, comercio)
            .orElse(null);

        return pontos != null ? pontos.getPontos() : 0;
    }

    public List<ClientePontoComercio> listarPontosPorCliente(UUID clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId);
        if (cliente == null) {
            throw new NaoEncontradoException("Cliente não encontrado: " + clienteId);
        }

        return clientePontoComercioRepository.findByCliente(cliente);
    }

    public List<ClientePontoComercio> listarPontosPorUsuario(UUID usuarioId) {
        Cliente cliente = clienteRepository.findByUsuarioId(usuarioId);
        if (cliente == null) {
            throw new NaoEncontradoException("Cliente não encontrado para o usuário: " + usuarioId);
        }

        return clientePontoComercioRepository.findByCliente(cliente);
    }

    public List<ClientePontoComercio> listarPontosPorComercio(UUID comercioId) {
        Comercio comercio = comercioRepository.findById(comercioId);
        if (comercio == null) {
            throw new NaoEncontradoException("Comércio não encontrado: " + comercioId);
        }

        return clientePontoComercioRepository.findByComercio(comercio);
    }
}