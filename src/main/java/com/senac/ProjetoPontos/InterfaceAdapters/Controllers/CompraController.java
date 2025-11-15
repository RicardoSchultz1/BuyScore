package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import com.senac.ProjetoPontos.Aplication.UseCase.ClienteUseCase;
import com.senac.ProjetoPontos.Aplication.UseCase.CompraUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Compra;
import com.senac.ProjetoPontos.Infrastructure.Security.UsuarioDetails;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.CompraRequest;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.CompraResponse;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private final CompraUseCase compraUseCase;
    private final ClienteUseCase clienteUseCase;


    public CompraController(CompraUseCase compraUseCase, ClienteUseCase clienteUseCase) {
        this.compraUseCase = compraUseCase;
        this.clienteUseCase = clienteUseCase;
    }

    @PostMapping
    public ResponseEntity<String> criarCompra(@RequestBody CompraRequest request, Authentication authentication) {
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        UUID usuarioId = userDetails.getUsuario().getId();

        String codigo = compraUseCase.criarCompraComCodigo(
            clienteUseCase.buscarClientePorUsuarioId(usuarioId).getId(), 
            request.getProdutoId(), 
            request.getQuantidade()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(codigo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Compra>> listarTodasCompras() {
        List<Compra> compras = compraUseCase.listarTodasCompras();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarCompra(@PathVariable UUID id) {
        Compra compra = compraUseCase.buscarCompra(id);
        return ResponseEntity.ok(compra);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Compra>> listarComprasPorCliente(@PathVariable UUID clienteId) {
        List<Compra> compras = compraUseCase.listarComprasPorCliente(clienteId);
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Compra>> listarComprasPorProduto(@PathVariable UUID produtoId) {
        List<Compra> compras = compraUseCase.listarComprasPorProduto(produtoId);
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Compra>> listarComprasPorStatus(@PathVariable String status) {
        List<Compra> compras = compraUseCase.listarComprasPorStatus(status);
        return ResponseEntity.ok(compras);
    }

    @PutMapping("/confirmar/{codigo}")
    public ResponseEntity<Compra> confirmarCompra(@PathVariable String codigo) {
        Compra compra = compraUseCase.confirmarCompraPorCodigo(codigo);
        return ResponseEntity.ok(compra);
    }

    @PutMapping("/cancelar/{codigo}")
    public ResponseEntity<Compra> cancelarCompra(@PathVariable String codigo) {
        Compra compra = compraUseCase.cancelarCompraPorCodigo(codigo);
        return ResponseEntity.ok(compra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCompra(@PathVariable UUID id) {
        compraUseCase.deletarCompra(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CompraResponse> buscarCompraPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(compraUseCase.buscarCompraPorCodigo(codigo));
    }
}