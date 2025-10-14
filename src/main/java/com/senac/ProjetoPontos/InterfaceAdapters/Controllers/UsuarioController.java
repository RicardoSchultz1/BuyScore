package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.senac.ProjetoPontos.Aplication.UseCase.UsuarioUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioUseCase useCase;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    public UsuarioController(UsuarioUseCase useCase) {
        this.useCase = useCase;
    }

    
    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable UUID id) {
        return useCase.buscarUsuario(id);
    }

    // Criar usuario
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario salvo = useCase.salvarUsuario(usuario);
        return ResponseEntity.ok(salvo);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> findAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("Authentication at UsuarioController.findAll: principal='{}', authorities='{}', authenticated={}", auth != null ? auth.getPrincipal() : null, auth != null ? auth.getAuthorities() : null, auth != null ? auth.isAuthenticated() : false);
        List<Usuario> usuarios = useCase.findAll();
        return ResponseEntity.ok(usuarios);
    }

}
