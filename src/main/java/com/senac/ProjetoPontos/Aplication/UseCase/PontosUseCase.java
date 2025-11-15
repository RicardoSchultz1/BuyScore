package com.senac.ProjetoPontos.Aplication.UseCase;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.ClientePontoComercio;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Ponto;
import com.senac.ProjetoPontos.Domain.Entity.Produto;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ClienteRepository;
import com.senac.ProjetoPontos.Domain.Repository.ClientePontoComercioRepository;
import com.senac.ProjetoPontos.Domain.Repository.ComercioRepository;
import com.senac.ProjetoPontos.Domain.Repository.CompraRepository;
import com.senac.ProjetoPontos.Domain.Repository.PontoRepository;
import com.senac.ProjetoPontos.Domain.Repository.UsuarioRepository;
import com.senac.ProjetoPontos.Infrastructure.Config.SecureRandom;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.CompraRequest;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaMensalResponse;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaCompraMensalResponse;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaPontosResgatadosResponse;

@Service
public class PontosUseCase {
    
    private static final Logger logger = LoggerFactory.getLogger(PontosUseCase.class);
    
    private final PontoRepository pontoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComercioRepository comercioRepository;
    private final ClienteRepository clienteRepository;
    private final CompraRepository compraRepository;
    private final ClientePontoComercioRepository clientePontoComercioRepository;

    public PontosUseCase(PontoRepository pontoRepository, UsuarioRepository usuarioRepository, 
                        ComercioRepository comercioRepository, ClienteRepository clienteRepository,
                        CompraRepository compraRepository, ClientePontoComercioRepository clientePontoComercioRepository) {
        this.pontoRepository = pontoRepository;
        this.usuarioRepository = usuarioRepository;
        this.comercioRepository = comercioRepository;
        this.clienteRepository = clienteRepository;
        this.compraRepository = compraRepository;
        this.clientePontoComercioRepository = clientePontoComercioRepository;
    }

    public String criarPontos(int pontos, UUID usuarioId) {
    Usuario usuario = usuarioRepository.findById(usuarioId);
    if (usuario == null) {
        throw new NaoEncontradoException("Usuário não encontrado: " + usuarioId);
    }

    Comercio comercio = comercioRepository.findByUsuarioId(usuario.getId()).get();
    if (comercio == null) {
        throw new NaoEncontradoException("Comércio não encontrado para o usuário: " + usuarioId);
    }
    Ponto ponto = new Ponto();
    String codigo = SecureRandom.generate6LetterCode();
    ponto.setCodigo(codigo);
    ponto.setPontos(pontos);
    ponto.setData(new java.sql.Date(System.currentTimeMillis()));
    ponto.setComercio(comercio);
    pontoRepository.save(ponto);
    return codigo;
    }

    public int getPontoByCodigo(String codigo, UUID usuarioId) {
        Ponto ponto = pontoRepository.getByCodigo(codigo);
        if (ponto == null) {
            throw new NaoEncontradoException("Ponto não encontrado com o código: " + codigo);
        }

        Cliente cliente = clienteRepository.findByUsuarioId(usuarioId);
        if (cliente == null) {
            throw new NaoEncontradoException("Cliente não encontrado para o usuário: " + usuarioId);
        }

        // Obter o comércio do ponto
        Comercio comercio = ponto.getComercio();
        if (comercio == null) {
            throw new IllegalStateException("Ponto não possui comércio associado");
        }

        // Buscar ou criar relacionamento cliente-comercio
        ClientePontoComercio clientePontoComercio = clientePontoComercioRepository
            .findByClienteAndComercio(cliente, comercio)
            .orElse(null);

        if (clientePontoComercio == null) {
            // Criar novo relacionamento com os pontos do código
            clientePontoComercio = new ClientePontoComercio(null, cliente, comercio, ponto.getPontos());
        } else {
            // Adicionar pontos ao saldo existente
            clientePontoComercio.setPontos(clientePontoComercio.getPontos() + ponto.getPontos());
        }

        // Salvar o relacionamento cliente-comercio-pontos
        clientePontoComercioRepository.save(clientePontoComercio);

        // Associar o cliente ao ponto e limpar o código (marcando como usado)
        ponto.setCliente(cliente);
        ponto.setCodigo(null);
        pontoRepository.update(ponto);

        return ponto.getPontos();
    }

    // Estatística: clientes únicos que resgataram pontos por mês em um comércio
    public List<EstatisticaMensalResponse> obterEstatisticasClientesPorMes(UUID usuarioId) {
        logger.info("Buscando estatísticas para usuarioId: {}", usuarioId);
        
        // Busca o comércio do usuário logado
        Comercio comercio = comercioRepository.findByUsuarioId(usuarioId).orElse(null);
        if (comercio == null) {
            logger.warn("Comércio não encontrado para usuário: {}", usuarioId);
            throw new NaoEncontradoException("Comércio não encontrado para o usuário: " + usuarioId);
        }
        
        logger.info("Comércio encontrado: ID={}, Nome={}", comercio.getId(), comercio.getRazaoSocial());
        
        // Usa o ID do comércio para buscar as estatísticas
        List<EstatisticaMensalResponse> resultado = pontoRepository.contarClientesPorMesPorComercio(comercio.getId());
        logger.info("Resultado da query: {} registros encontrados", resultado.size());
        
        return resultado;
    }

    // Estatística: número de compras realizadas por mês em um comércio
    public List<EstatisticaCompraMensalResponse> obterEstatisticasComprasPorMes(UUID usuarioId) {
        logger.info("Buscando estatísticas de compras para usuarioId: {}", usuarioId);
        
        // Busca o comércio do usuário logado
        Comercio comercio = comercioRepository.findByUsuarioId(usuarioId).orElse(null);
        if (comercio == null) {
            logger.warn("Comércio não encontrado para usuário: {}", usuarioId);
            throw new NaoEncontradoException("Comércio não encontrado para o usuário: " + usuarioId);
        }
        
        logger.info("Comércio encontrado para compras: ID={}, Nome={}", comercio.getId(), comercio.getRazaoSocial());
        
        // Usa o ID do comércio para buscar as estatísticas de compras
        List<EstatisticaCompraMensalResponse> resultado = compraRepository.contarComprasPorMesPorComercio(comercio.getId());
        logger.info("Resultado da query de compras: {} registros encontrados", resultado.size());
        
        return resultado;
    }

    // Estatística: soma total de pontos resgatados por mês em um comércio
    public List<EstatisticaPontosResgatadosResponse> obterSomaPontosResgatadosPorMes(UUID usuarioId) {
        logger.info("Buscando soma de pontos resgatados por mês para usuarioId: {}", usuarioId);
        
        // Busca o comércio do usuário logado
        Comercio comercio = comercioRepository.findByUsuarioId(usuarioId).orElse(null);
        if (comercio == null) {
            logger.warn("Comércio não encontrado para usuário: {}", usuarioId);
            throw new NaoEncontradoException("Comércio não encontrado para o usuário: " + usuarioId);
        }
        
        logger.info("Comércio encontrado para soma de pontos: ID={}, Nome={}", comercio.getId(), comercio.getRazaoSocial());
        
        // Usa o ID do comércio para buscar a soma de pontos resgatados
        List<EstatisticaPontosResgatadosResponse> resultado = pontoRepository.somarPontosResgatadosPorMesPorComercio(comercio.getId());
        logger.info("Resultado da query de soma de pontos: {} registros encontrados", resultado.size());
        
        return resultado;
    }

}
