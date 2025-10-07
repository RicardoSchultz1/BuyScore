package com.senac.ProjetoPontos.Aplication.UseCase;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ComercioRepository;
import com.senac.ProjetoPontos.Domain.Repository.UsuarioRepository;

@Service
public class ComercioUseCase {
    
    private final ComercioRepository comercioRepository;
    private final UsuarioRepository usuarioRepository;

    public ComercioUseCase(ComercioRepository comercioRepository, UsuarioRepository usuarioRepository) {
        this.comercioRepository = comercioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Comercio salvarComercioEntity(Usuario usuario, String CNPJ, String razaoSocial, String descricao, String seguimento, Usuario matriz) {
        Comercio comercio = new Comercio(UUID.randomUUID(), usuario, CNPJ, razaoSocial, descricao, seguimento, matriz);
        comercioRepository.save(comercio);
        return comercio;
    }

    public Comercio salvarComercio(Comercio comercio) {
        comercio.setUsuario(usuarioRepository.findById(comercio.getUsuario().getId())); 
        return comercioRepository.save(comercio);
    }

    public Comercio buscarComercio(UUID id) {
        Comercio comercio = comercioRepository.findById(id);
        if (comercio == null) {
            throw new NaoEncontradoException("" + id);
        }
        return comercio;
    }

    public List<Comercio> findAll() {
        return comercioRepository.findAll();
    }
}
