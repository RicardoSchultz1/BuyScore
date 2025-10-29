package com.senac.ProjetoPontos.Aplication.UseCase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Ponto;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ClienteRepository;
import com.senac.ProjetoPontos.Domain.Repository.ComercioRepository;
import com.senac.ProjetoPontos.Domain.Repository.PontoRepository;
import com.senac.ProjetoPontos.Domain.Repository.UsuarioRepository;
import com.senac.ProjetoPontos.Infrastructure.Config.SecureRandom;

@Service
public class PontosUseCase {
    
    private final PontoRepository pontoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComercioRepository comercioRepository;
    private final ClienteRepository clienteRepository;

    public PontosUseCase(PontoRepository pontoRepository, UsuarioRepository usuarioRepository, ComercioRepository comercioRepository, ClienteRepository clienteRepository) {
        this.pontoRepository = pontoRepository;
        this.usuarioRepository = usuarioRepository;
        this.comercioRepository = comercioRepository;
        this.clienteRepository = clienteRepository;
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
        ponto.setCliente(cliente);
        ponto.setCodigo(null);
        pontoRepository.update(ponto);
        return ponto.getPontos();
    }

}
