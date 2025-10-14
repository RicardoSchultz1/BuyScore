package com.senac.ProjetoPontos.Aplication.UseCase;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Endereco;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.UsuarioRepository;

@Service
public class UsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

     public UsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuarioEntity(String nome, String email, String senha, int perfilUsuario, String fotoPerfil, Endereco endereco) {
        Usuario usuario = new Usuario(null, nome, email, senha, perfilUsuario, fotoPerfil, endereco);
        if(usuarioRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuario(UUID id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new NaoEncontradoException("" + id);
        }
        return usuario;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

}
