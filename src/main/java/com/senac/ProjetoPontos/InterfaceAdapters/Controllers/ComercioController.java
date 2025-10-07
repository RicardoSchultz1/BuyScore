package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.ComercioUseCase;
import com.senac.ProjetoPontos.Aplication.UseCase.UsuarioUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.InterfaceAdapters.DTOs.ComercioRequest;

@RestController
@RequestMapping("/comercio")
public class ComercioController {
    
    private final ComercioUseCase useCase;
    private final UsuarioUseCase usuarioUseCase;

    public ComercioController(ComercioUseCase useCase, UsuarioUseCase usuarioUseCase) {
        this.useCase = useCase;
        this.usuarioUseCase = usuarioUseCase;
    }

    // Buscar comercio por ID
    @GetMapping("/{id}")
    public Comercio buscarComercio(@PathVariable UUID id) {
        return useCase.buscarComercio(id);
    }

    // Criar comercio
    @PostMapping
    public ResponseEntity<Comercio> criarComercio(@RequestBody ComercioRequest request) {
        Usuario usuario = usuarioUseCase.buscarUsuario(request.getUsuarioId());
        Usuario matriz = null;
        if (request.getMatrizId() != null) {
            matriz = usuarioUseCase.buscarUsuario(request.getMatrizId());
        }

        Comercio salvo = useCase.salvarComercioEntity(usuario, request.getCNPJ(), request.getRazaoSocial(), request.getDescricao(), request.getSeguimento(), matriz);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comercio>> findAll() {
        List<Comercio> comercios = useCase.findAll();
        return ResponseEntity.ok(comercios);
    }
}
