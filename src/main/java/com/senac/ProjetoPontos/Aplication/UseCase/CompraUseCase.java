package com.senac.ProjetoPontos.Aplication.UseCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.Compra;
import com.senac.ProjetoPontos.Domain.Entity.Produto;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ClienteRepository;
import com.senac.ProjetoPontos.Domain.Repository.CompraRepository;
import com.senac.ProjetoPontos.Domain.Repository.ProdutoRepository;

@Service
public class CompraUseCase {
    
    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public CompraUseCase(CompraRepository compraRepository, ClienteRepository clienteRepository, 
                        ProdutoRepository produtoRepository) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
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
        Double valorTotal = produto.getValor() * quantidade.doubleValue();
        int valorTotalInt = (int) Math.round(valorTotal);

        if(cliente.getPontos() < valorTotalInt) {
            throw new IllegalArgumentException("Saldo de pontos insuficiente para realizar a compra.");
        }
        Compra compra = new Compra(
            null,
            cliente,
            produto,
            quantidade,
            valorTotal, // Mantendo como Double para a entidade Compra
            LocalDateTime.now(),
            "PENDENTE"
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
        int valorTotalInt = (int) Math.round(compra.getValorTotal());
        
        if(cliente.getPontos() < valorTotalInt) {
            throw new IllegalArgumentException("Saldo de pontos insuficiente para confirmar a compra.");
        } else {
            cliente.setPontos(cliente.getPontos() - valorTotalInt);
            clienteRepository.update(cliente);
        }
        
        compra.setStatus("CONFIRMADA");
        compraRepository.update(compra);
        return compra;
    }

    public Compra cancelarCompra(UUID compraId) {
        Compra compra = buscarCompra(compraId);
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
}