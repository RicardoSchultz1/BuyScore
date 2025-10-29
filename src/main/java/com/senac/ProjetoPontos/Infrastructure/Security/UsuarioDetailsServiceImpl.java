package com.senac.ProjetoPontos.Infrastructure.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.senac.ProjetoPontos.Domain.Repository.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioDetailsServiceImpl.class);
    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Tentando carregar usuário: {}", username);
        
        var usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            logger.error("Usuário não encontrado: {}", username);
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        
        logger.info("Usuário encontrado: {} - Senha hash: {}", username, 
                    usuario.getSenha() != null ? usuario.getSenha().substring(0, Math.min(10, usuario.getSenha().length())) + "..." : "null");
        
        return new UsuarioDetails(usuario);
    }
}
