package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.ComercioUseCase;
import com.senac.ProjetoPontos.Aplication.UseCase.PontosUseCase;
import com.senac.ProjetoPontos.Aplication.UseCase.PontoUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Ponto;
import com.senac.ProjetoPontos.Domain.Entity.ClientePontoComercio;
import com.senac.ProjetoPontos.Infrastructure.Security.UsuarioDetails;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaMensalResponse;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaCompraMensalResponse;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.EstatisticaPontosResgatadosResponse;

@RestController
@RequestMapping("/ponto")
public class PontoController {

    private static final Logger logger = LoggerFactory.getLogger(PontoController.class);
    
    private final PontosUseCase pontosUseCase;
    private final ComercioUseCase comercioUseCase;
    private final PontoUseCase pontoUseCase;

    public PontoController(PontosUseCase pontosUseCase, ComercioUseCase comercioUseCase, PontoUseCase pontoUseCase) {
        this.pontosUseCase = pontosUseCase;
        this.comercioUseCase = comercioUseCase;
        this.pontoUseCase = pontoUseCase;
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
        try {
            UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
            UUID usuarioId = userDetails.getUsuario().getId();
            
            logger.info("Iniciando busca de estatísticas para usuário: {}", usuarioId);
            
            List<EstatisticaMensalResponse> estatisticas = pontosUseCase.obterEstatisticasClientesPorMes(usuarioId);
            
            if (estatisticas.isEmpty()) {
                logger.info("Nenhuma estatística encontrada para usuário: {}", usuarioId);
                return ResponseEntity.ok(estatisticas); // Retorna 200 com lista vazia
            }
            
            logger.info("Estatísticas encontradas: {} registros para usuário: {}", estatisticas.size(), usuarioId);
            return ResponseEntity.ok(estatisticas);
            
        } catch (Exception e) {
            logger.error("Erro ao buscar estatísticas", e);
            return ResponseEntity.internalServerError().build(); // 500 em vez de 404
        }
    }

    @GetMapping("/estatisticas/compras")
    public ResponseEntity<List<EstatisticaCompraMensalResponse>> obterEstatisticasComprasPorMes(Authentication authentication) {
        try {
            UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
            UUID usuarioId = userDetails.getUsuario().getId();
            
            logger.info("Iniciando busca de estatísticas de compras para usuário: {}", usuarioId);
            
            List<EstatisticaCompraMensalResponse> estatisticas = pontosUseCase.obterEstatisticasComprasPorMes(usuarioId);
            
            if (estatisticas.isEmpty()) {
                logger.info("Nenhuma estatística de compra encontrada para usuário: {}", usuarioId);
                return ResponseEntity.ok(estatisticas); // Retorna 200 com lista vazia
            }
            
            logger.info("Estatísticas de compras encontradas: {} registros para usuário: {}", estatisticas.size(), usuarioId);
            return ResponseEntity.ok(estatisticas);
            
        } catch (Exception e) {
            logger.error("Erro ao buscar estatísticas de compras", e);
            return ResponseEntity.internalServerError().build(); // 500 em vez de 404
        }
    }

    @GetMapping("/meus-pontos-por-comercio")
    public ResponseEntity<List<ClientePontoComercio>> obterMeusPontosPorComercio(Authentication authentication) {
        try {
            UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
            UUID usuarioId = userDetails.getUsuario().getId();
            
            logger.info("Buscando pontos por comércio para usuário: {}", usuarioId);
            
            // Buscar o cliente pelo usuário
            List<ClientePontoComercio> pontosPorComercio = pontoUseCase.listarPontosPorUsuario(usuarioId);
            
            logger.info("Pontos por comércio encontrados: {} registros para usuário: {}", pontosPorComercio.size(), usuarioId);
            return ResponseEntity.ok(pontosPorComercio);
            
        } catch (Exception e) {
            logger.error("Erro ao buscar pontos por comércio", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/estatisticas/pontos-resgatados")
    public ResponseEntity<List<EstatisticaPontosResgatadosResponse>> obterSomaPontosResgatadosPorMes(Authentication authentication) {
        try {
            UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
            UUID usuarioId = userDetails.getUsuario().getId();
            
            logger.info("Iniciando busca de soma de pontos resgatados para usuário: {}", usuarioId);
            
            List<EstatisticaPontosResgatadosResponse> estatisticas = pontosUseCase.obterSomaPontosResgatadosPorMes(usuarioId);
            
            if (estatisticas.isEmpty()) {
                logger.info("Nenhuma estatística de pontos resgatados encontrada para usuário: {}", usuarioId);
                return ResponseEntity.ok(estatisticas); // Retorna 200 com lista vazia
            }
            
            logger.info("Estatísticas de pontos resgatados encontradas: {} registros para usuário: {}", estatisticas.size(), usuarioId);
            return ResponseEntity.ok(estatisticas);
            
        } catch (Exception e) {
            logger.error("Erro ao buscar estatísticas de pontos resgatados", e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
