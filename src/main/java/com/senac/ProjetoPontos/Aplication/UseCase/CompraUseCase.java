package com.senac.ProjetoPontos.Aplication.UseCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.ClientePontoComercio;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Compra;
import com.senac.ProjetoPontos.Domain.Entity.Produto;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ClienteRepository;
import com.senac.ProjetoPontos.Domain.Repository.ClientePontoComercioRepository;
import com.senac.ProjetoPontos.Domain.Repository.CompraRepository;
import com.senac.ProjetoPontos.Domain.Repository.ProdutoRepository;
import com.senac.ProjetoPontos.Infrastructure.Config.SecureRandom;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.CompraResponse;

@Service
public class CompraUseCase {
    
    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ClientePontoComercioRepository clientePontoComercioRepository;

    public CompraUseCase(CompraRepository compraRepository, ClienteRepository clienteRepository, 
                        ProdutoRepository produtoRepository, ClientePontoComercioRepository clientePontoComercioRepository) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.clientePontoComercioRepository = clientePontoComercioRepository;
    }

    // Métodos auxiliares para gerenciar pontos por comércio
    private int obterPontosClienteComercio(Cliente cliente, Comercio comercio) {
        ClientePontoComercio pontoComercio = clientePontoComercioRepository
            .findByClienteAndComercio(cliente, comercio)
            .orElse(null);
            
        return pontoComercio != null ? pontoComercio.getPontos() : 0;
    }
    
    private void atualizarPontosClienteComercio(Cliente cliente, Comercio comercio, int novoValor) {
        ClientePontoComercio pontoComercio = clientePontoComercioRepository
            .findByClienteAndComercio(cliente, comercio)
            .orElse(null);
            
        if (pontoComercio == null) {
            // Criar novo relacionamento
            pontoComercio = new ClientePontoComercio(null, cliente, comercio, novoValor);
        } else {
            // Atualizar existente
            pontoComercio.setPontos(novoValor);
        }
        
        clientePontoComercioRepository.save(pontoComercio);
    }

    public Compra criarCompra(UUID clienteId, UUID produtoId, Integer quantidade) {
        Cliente cliente = clienteRepository.findById(clienteId);
        if (cliente == null) {
            throw new NaoEncontradoException("Cliente não encontrado: " + clienteId);
        }

        Produto produto = produtoRepository.findById(produtoId);
        if (produto == null) {
            throw new NaoEncontradoException("Produto não encontrado: " + produtoId);
        }
        
        Comercio comercio = produto.getComercio();
        if (comercio == null) {
            throw new IllegalArgumentException("Produto não possui comércio associado");
        }
        
        Double valorTotal = produto.getValor() * quantidade.doubleValue();
        int valorTotalInt = (int) Math.round(valorTotal);

        // Verifica pontos do cliente neste comércio específico
        int pontosDisponiveis = obterPontosClienteComercio(cliente, comercio);
        if(pontosDisponiveis < valorTotalInt) {
            throw new IllegalArgumentException("Saldo de pontos insuficiente para realizar a compra neste comércio. Disponível: " + pontosDisponiveis + ", necessário: " + valorTotalInt);
        }
        
        // Subtrai os pontos do cliente no comércio específico
        atualizarPontosClienteComercio(cliente, comercio, pontosDisponiveis - valorTotalInt);
        
        // Gera o código antes de criar a compra
        String codigo = SecureRandom.generate6LetterCode();
        
        Compra compra = new Compra(
            null,
            cliente,
            produto,
            quantidade,
            valorTotal, // Mantendo como Double para a entidade Compra
            LocalDateTime.now(),
            "CONFIRMADA", // Já criada como confirmada pois pontos foram debitados
            codigo
        );

        return compraRepository.save(compra);
    }

    public Compra buscarCompra(UUID id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Compra não encontrada: " + id));
    }

    public List<Compra> listarTodasCompras() {
        return compraRepository.findAll();
    }

    public List<Compra> listarComprasPorCliente(UUID clienteId) {
        return compraRepository.findByClienteId(clienteId);
    }

    public List<Compra> listarComprasPorProduto(UUID produtoId) {
        return compraRepository.findByProdutoId(produtoId);
    }

    public List<Compra> listarComprasPorStatus(String status) {
        return compraRepository.findByStatus(status);
    }

    public Compra confirmarCompra(UUID compraId) {
        Compra compra = buscarCompra(compraId);
        Cliente cliente = clienteRepository.findById(compra.getCliente().getId());
        Comercio comercio = compra.getProduto().getComercio();
        int valorTotalInt = (int) Math.round(compra.getValorTotal());
        
        int pontosDisponiveis = obterPontosClienteComercio(cliente, comercio);
        if(pontosDisponiveis < valorTotalInt) {
            throw new IllegalArgumentException("Saldo de pontos insuficiente para confirmar a compra neste comércio. Disponível: " + pontosDisponiveis + ", necessário: " + valorTotalInt);
        } else {
            atualizarPontosClienteComercio(cliente, comercio, pontosDisponiveis - valorTotalInt);
        }
        
        compra.setStatus("CONFIRMADA");
        compraRepository.update(compra);
        return compra;
    }

    public Compra cancelarCompra(UUID compraId) {
        Compra compra = buscarCompra(compraId);
        // Se já estava confirmada, devolver os pontos ao cliente no comércio específico
        if ("CONFIRMADA".equalsIgnoreCase(compra.getStatus())) {
            Cliente cliente = clienteRepository.findById(compra.getCliente().getId());
            Comercio comercio = compra.getProduto().getComercio();
            int valorTotalInt = (int) Math.round(compra.getValorTotal());
            
            int pontosAtuais = obterPontosClienteComercio(cliente, comercio);
            atualizarPontosClienteComercio(cliente, comercio, pontosAtuais + valorTotalInt);
        }

        compra.setStatus("CANCELADA");
        compraRepository.update(compra);
        return compra;
    }

    // Métodos que usam código em vez de ID
    public Compra confirmarCompraPorCodigo(String codigo) {
        Compra compra = compraRepository.findByCodigo(codigo);
        if (compra == null) {
            throw new NaoEncontradoException("Compra não encontrada com o código: " + codigo);
        }
        
        // Se já está confirmada, não faz nada
        if ("CONFIRMADA".equalsIgnoreCase(compra.getStatus())) {
            return compra;
        }
        
        Cliente cliente = clienteRepository.findById(compra.getCliente().getId());
        Comercio comercio = compra.getProduto().getComercio();
        int valorTotalInt = (int) Math.round(compra.getValorTotal());
        
        // Verifica pontos do cliente neste comércio específico
        int pontosDisponiveis = obterPontosClienteComercio(cliente, comercio);
        if(pontosDisponiveis < valorTotalInt) {
            throw new IllegalArgumentException("Saldo de pontos insuficiente para confirmar a compra neste comércio. Disponível: " + pontosDisponiveis + ", necessário: " + valorTotalInt);
        } else {
            atualizarPontosClienteComercio(cliente, comercio, pontosDisponiveis - valorTotalInt);
        }
        
        compra.setStatus("CONFIRMADA");
        compraRepository.update(compra);
        return compra;
    }

    public Compra cancelarCompraPorCodigo(String codigo) {
        Compra compra = compraRepository.findByCodigo(codigo);
        if (compra == null) {
            throw new NaoEncontradoException("Compra não encontrada com o código: " + codigo);
        }

        // Se já está cancelada, não faz nada
        if ("CANCELADA".equalsIgnoreCase(compra.getStatus())) {
            return compra;
        }

        // Se estava confirmada, devolve os pontos ao comércio específico
        if ("CONFIRMADA".equalsIgnoreCase(compra.getStatus())) {
            Cliente cliente = clienteRepository.findById(compra.getCliente().getId());
            Comercio comercio = compra.getProduto().getComercio();
            int valorTotalInt = (int) Math.round(compra.getValorTotal());
            
            int pontosAtuais = obterPontosClienteComercio(cliente, comercio);
            atualizarPontosClienteComercio(cliente, comercio, pontosAtuais + valorTotalInt);
        }

        compra.setStatus("CANCELADA");
        compraRepository.update(compra);
        return compra;
    }

    public void deletarCompra(UUID id) {
        if (!compraRepository.findById(id).isPresent()) {
            throw new NaoEncontradoException("Compra não encontrada: " + id);
        }
        compraRepository.delete(id);
    }

    public String criarCompraComCodigo(UUID clienteId, UUID produtoId, Integer quantidade) {
        Compra compra = criarCompra(clienteId, produtoId, quantidade);
        return compra.getCodigo();
    }

    public CompraResponse buscarCompraPorCodigo(String codigo) {
        Compra compra = compraRepository.findByCodigo(codigo);
        if (compra == null) {
            throw new NaoEncontradoException("Compra não encontrada com o código: " + codigo);
        }
        CompraResponse response = new CompraResponse(compra.getProduto().getNome(), compra.getQuantidade());
        return response;
    }
}