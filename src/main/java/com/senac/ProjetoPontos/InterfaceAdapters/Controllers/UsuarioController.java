package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.UsuarioUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioUseCase useCase;

    public UsuarioController(UsuarioUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable UUID id) {
        return useCase.buscarUsuario(id);
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario salvo = useCase.salvarUsuario(usuario);
        return ResponseEntity.ok(salvo);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = useCase.findAll();
        return ResponseEntity.ok(usuarios);
    }
}
