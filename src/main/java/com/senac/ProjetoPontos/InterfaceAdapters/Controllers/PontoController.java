package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.ComercioUseCase;
import com.senac.ProjetoPontos.Aplication.UseCase.PontosUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Ponto;
import com.senac.ProjetoPontos.Infrastructure.Security.UsuarioDetails;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaMensalResponse;

@RestController
@RequestMapping("/ponto")
public class PontoController {

    private final PontosUseCase pontosUseCase;
    private final ComercioUseCase comercioUseCase;

    public PontoController(PontosUseCase pontosUseCase, ComercioUseCase comercioUseCase) {
        this.pontosUseCase = pontosUseCase;
        this.comercioUseCase = comercioUseCase;
    }

    @PostMapping("/criarponto")
    public ResponseEntity<String> criarPonto(@RequestBody Ponto ponto, Authentication authentication) {
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        UUID usuarioId = userDetails.getUsuario().getId();
        //UUID comercioId = comercioUseCase.buscarComercioPorUsuarioId(usuarioId).getId();
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

    @GetMapping("/estatisticas")
    public ResponseEntity<List<EstatisticaMensalResponse>> obterEstatisticasClientesPorMes(Authentication authentication) {

        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        UUID usuarioId = userDetails.getUsuario().getId();
        //UUID usuarioId = userDetails.getUsuario().getId();
        try {
            List<EstatisticaMensalResponse> estatisticas = pontosUseCase.obterEstatisticasClientesPorMes(usuarioId);
            return ResponseEntity.ok(estatisticas);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
