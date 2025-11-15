package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.PontoUseCase;
import com.senac.ProjetoPontos.Domain.Entity.ClientePontoComercio;

@RestController
@RequestMapping("/api/pontos-comercio")
@CrossOrigin(origins = "*")
public class ClientePontoComercioController {

    private final PontoUseCase pontoUseCase;

    public ClientePontoComercioController(PontoUseCase pontoUseCase) {
        this.pontoUseCase = pontoUseCase;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarPontos(@RequestParam UUID clienteId,
                                                 @RequestParam UUID comercioId,
                                                 @RequestParam int pontos) {
        pontoUseCase.adicionarPontos(clienteId, comercioId, pontos);
        return ResponseEntity.ok("Pontos adicionados com sucesso");
    }

    @PostMapping("/definir")
    public ResponseEntity<String> definirPontos(@RequestParam UUID clienteId,
                                               @RequestParam UUID comercioId,
                                               @RequestParam int pontos) {
        pontoUseCase.definirPontos(clienteId, comercioId, pontos);
        return ResponseEntity.ok("Pontos definidos com sucesso");
    }

    @GetMapping("/consultar")
    public ResponseEntity<Integer> obterPontos(@RequestParam UUID clienteId,
                                              @RequestParam UUID comercioId) {
        int pontos = pontoUseCase.obterPontos(clienteId, comercioId);
        return ResponseEntity.ok(pontos);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ClientePontoComercio>> listarPontosPorCliente(@PathVariable UUID clienteId) {
        List<ClientePontoComercio> pontos = pontoUseCase.listarPontosPorCliente(clienteId);
        return ResponseEntity.ok(pontos);
    }

    @GetMapping("/comercio/{comercioId}")
    public ResponseEntity<List<ClientePontoComercio>> listarPontosPorComercio(@PathVariable UUID comercioId) {
        List<ClientePontoComercio> pontos = pontoUseCase.listarPontosPorComercio(comercioId);
        return ResponseEntity.ok(pontos);
    }
}