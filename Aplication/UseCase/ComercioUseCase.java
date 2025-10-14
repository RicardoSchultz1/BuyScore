package com.senac.ProjetoPontos.Aplication.UseCase;

import java.util.List;
import java.util.Optional;
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
    private     final UsuarioRepository usuarioRepository;

    public ComercioUseCase(ComercioRepository comercioRepository, UsuarioRepository usuarioRepository) {
        this.comercioRepository = comercioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Comercio salvarComercioEntity(Usuario usuario, String cnpj, String razaoSocial, String descricao, String seguimento, Usuario matriz) {
        if (cnpj != null && comercioRepository.findByCnpj(cnpj).isPresent()) {
            throw new IllegalArgumentException("Cnpj já cadastrado");
        }
        if (usuario.getPerfilUsuario() != 2) {
            throw new IllegalArgumentException("Usuário não possui perfil de comércio");
        }
        try {
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário", e);
        }
       
    Comercio comercio = new Comercio(UUID.randomUUID(), usuarioRepository.findByEmail(usuario.getEmail()), cnpj, razaoSocial, descricao, seguimento, matriz);
        try {
            comercioRepository.save(comercio);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar comércio", e);
        }
        return comercio;
    }
    public Comercio salvarComercioUser(Comercio comercio, Usuario usuario) {
        if (comercioRepository.existsByUsuarioId(comercio.getUsuario().getId())) {
            throw new IllegalArgumentException("Usuário já possui comércio");
        }
        if (comercio.getUsuario().getPerfilUsuario() != 2) {
            throw new IllegalArgumentException("Usuário não possui perfil de comércio");
        }
        
        comercio.setId(UUID.randomUUID());
        comercio.setUsuario(usuarioRepository.findById(comercio.getUsuario().getId())); 
        return comercioRepository.save(comercio);
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

    public Optional<Comercio> findByCnpj(String cnpj) {
        return comercioRepository.findByCnpj(cnpj);
    }
}
