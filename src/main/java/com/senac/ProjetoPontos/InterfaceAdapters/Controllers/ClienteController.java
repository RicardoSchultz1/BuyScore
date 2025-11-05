package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.ClienteUseCase;
import com.senac.ProjetoPontos.Aplication.UseCase.UsuarioUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Endereco;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.Infrastructure.Security.JwtUtil;
import com.senac.ProjetoPontos.Infrastructure.Security.UsuarioDetails;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.ClienteUserRequest;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.ClienteWithTokenResponse;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final UsuarioUseCase usuarioUseCase;
    private final JwtUtil jwtUtil;
    private final ClienteUseCase clienteService;

    public ClienteController(UsuarioUseCase usuarioUseCase, JwtUtil jwtUtil, ClienteUseCase clienteService) {
        this.usuarioUseCase = usuarioUseCase;
        this.jwtUtil = jwtUtil;
        this.clienteService = clienteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable UUID id) {
        Cliente cliente = clienteService.buscarCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteWithTokenResponse> criarCliente(@RequestBody ClienteUserRequest request) {
        Endereco endereco = new Endereco(null, request.getCep(), request.getLogradouro(), request.getComplemento(), request.getBairro(), request.getCidade(), request.getNumero(), request.getUf());
        Usuario usuario = new Usuario(null, request.getNome(), request.getEmail(), request.getSenha(), 1, request.getFotoUsuario(), endereco);

        Cliente salvo = clienteService.salvarClienteEntity(usuario);
        // gerar token para o usuário recém-criado
        String token = jwtUtil.generateToken(salvo.getUsuario().getEmail());
        ClienteWithTokenResponse resp = new ClienteWithTokenResponse(salvo, token);
        return ResponseEntity.ok(resp);
    }

    /*@PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente created = clienteService.salvarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCliente(@PathVariable UUID id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        clienteService.atualizarCliente(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable UUID id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/favoritos/{comercioId}")
    public ResponseEntity<Void> adicionarComercioFavorito(@PathVariable UUID comercioId, Authentication authentication) {
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        clienteService.adicionarComercioFavorito(clienteService.buscarClientePorUsuarioId(userDetails.getUsuario().getId()).getId(), comercioId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/favoritos/{comercioId}")
    public ResponseEntity<Void> removerComercioFavorito(@PathVariable UUID comercioId, Authentication authentication) {
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        clienteService.removerComercioFavorito(clienteService.buscarClientePorUsuarioId(userDetails.getUsuario().getId()).getId(), comercioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/favoritos")
    public ResponseEntity<List<Comercio>> listarComerciosFavoritos(Authentication authentication) {
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        List<Comercio> comerciosFavoritos = clienteService.listarComerciosFavoritos(clienteService.buscarClientePorUsuarioId(userDetails.getUsuario().getId()).getId());
        return ResponseEntity.ok(comerciosFavoritos);
    }

    @GetMapping("/favoritos/{comercioId}/check")
    public ResponseEntity<Boolean> isComercioFavorito(@PathVariable UUID comercioId, Authentication authentication) {
        UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();
        boolean isFavorito = clienteService.isComercioFavorito(clienteService.buscarClientePorUsuarioId(userDetails.getUsuario().getId()).getId(), comercioId);
        return ResponseEntity.ok(isFavorito);
    }

}
