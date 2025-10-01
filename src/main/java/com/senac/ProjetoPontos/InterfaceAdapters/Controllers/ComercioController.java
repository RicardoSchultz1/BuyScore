package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.ComercioUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;

@RestController
@RequestMapping("/comercio")
public class ComercioController {
    
    private final ComercioUseCase useCase;

    public ComercioController(ComercioUseCase useCase) {
        this.useCase = useCase;
    }

    // Buscar comercio por ID
    @GetMapping("/{id}")
    public Comercio buscarComercio(@PathVariable UUID id) {
        return useCase.buscarComercio(id);
    }

    // Criar comercio
    @PostMapping
    public ResponseEntity<Comercio> criarComercio(@RequestBody Comercio comercio) {
        Comercio salvo = useCase.salvarComercio(comercio);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comercio>> findAll() {
        List<Comercio> comercios = useCase.findAll();
        return ResponseEntity.ok(comercios);
    }
}
