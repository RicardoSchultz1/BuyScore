package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.ComercioUseCase;
import com.senac.ProjetoPontos.Aplication.UseCase.UsuarioUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Endereco;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.ComercioUserRequest;

@RestController
@RequestMapping("/comercio")
public class ComercioController {
    
    private final ComercioUseCase useCase;
    private final UsuarioUseCase usuarioUseCase;

    public ComercioController(ComercioUseCase useCase, UsuarioUseCase usuarioUseCase) {
        this.useCase = useCase;
        this.usuarioUseCase = usuarioUseCase;
    }

    @GetMapping("/{id}")
    public Comercio buscarComercio(@PathVariable UUID id) {
        return useCase.buscarComercio(id);
    }

    @PostMapping
    public ResponseEntity<Comercio> criarComercio(@RequestBody ComercioUserRequest request) {
        Endereco endereco = new Endereco(null, request.getCep(), request.getLogradouro(), request.getComplemento(), request.getBairro(), request.getCidade(), request.getNumero(), request.getUf());
        Usuario usuario = new Usuario(null, request.getNome(), request.getEmail(), request.getSenha(), request.getPerfilUsuario(), request.getFotoUsuario(), endereco);
        Usuario matriz = null;
        if (request.getMatrizId() != null && !request.getMatrizId().trim().isEmpty()) {
            var found = useCase.findByCnpj(request.getMatrizId().trim());
            if (found != null && found.isPresent()) {

                matriz = usuarioUseCase.buscarUsuario(found.get().getUsuario().getId());
            }
        }
        
        Comercio salvo = useCase.salvarComercioEntity(usuario, request.getCnpj(), request.getRazaoSocial(), request.getDescricao(), request.getSeguimento(), matriz);
        return ResponseEntity.ok(salvo);
    }

   /*  @PostMapping("/saveComercio")
    public ResponseEntity<Comercio> salvarComercio(@RequestBody ComercioUserRequest comercioUser) {
        //Ainda vai precisar salvar o endereço antes do usuario
        Usuario usuario = new Usuario(comercioUser.getNome(), comercioUser.getEmail(), comercioUser.getSenha(), comercioUser.getPerfilUsuario(), comercioUser.getFotoUsuario(), comercioUser.getIdEndereco());

        Comercio salvo = useCase.salvarComercioEntity(comercioUser, );
        return ResponseEntity.ok(salvo);
    } 
    */    

    @GetMapping("/all")
    public ResponseEntity<List<Comercio>> findAll() {
        List<Comercio> comercios = useCase.findAll();
        return ResponseEntity.ok(comercios);
    }
}
