package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.PontosUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Ponto;
import com.senac.ProjetoPontos.Infrastructure.Security.UsuarioDetails;

@RestController
@RequestMapping("/ponto")
public class PontoController {

    private final PontosUseCase pontosUseCase; 
    
    public PontoController(PontosUseCase pontosUseCase) {
        this.pontosUseCase = pontosUseCase;
    }

    @PostMapping("/criarponto")
    public ResponseEntity<String> criarPonto(@RequestBody Ponto ponto, Authentication authentication) {
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        UUID usuarioId = userDetails.getUsuario().getId();
        return ResponseEntity.ok(pontosUseCase.criarPontos(ponto.getPontos(), usuarioId));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Integer> getPontoByCodigo(@PathVariable String codigo, Authentication authentication) {
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        UUID usuarioId = userDetails.getUsuario().getId();
        if(codigo == null || codigo.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        int pontos = pontosUseCase.getPontoByCodigo(codigo, usuarioId);
        return ResponseEntity.ok(pontos);
    }

}
