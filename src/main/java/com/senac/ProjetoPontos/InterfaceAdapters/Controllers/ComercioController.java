package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.ComercioUseCase;
import com.senac.ProjetoPontos.Aplication.UseCase.UsuarioUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Endereco;
import com.senac.ProjetoPontos.Domain.Entity.Ponto;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.ComercioUserRequest;
import com.senac.ProjetoPontos.InterfaceAdapters.DTO.ComercioWithTokenResponse;
import com.senac.ProjetoPontos.Infrastructure.Security.JwtUtil;

@RestController
@RequestMapping("/comercio")
public class ComercioController {
    
    private final ComercioUseCase useCase;
    private final UsuarioUseCase usuarioUseCase;
    private final JwtUtil jwtUtil;

    public ComercioController(ComercioUseCase useCase, UsuarioUseCase usuarioUseCase, JwtUtil jwtUtil) {
        this.useCase = useCase;
        this.usuarioUseCase = usuarioUseCase;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/{id}")
    public Comercio buscarComercio(@PathVariable UUID id) {
        return useCase.buscarComercio(id);
    }

    @PostMapping
    public ResponseEntity<ComercioWithTokenResponse> criarComercio(@RequestBody ComercioUserRequest request) {
        Endereco endereco = new Endereco(null, request.getCep(), request.getLogradouro(), request.getComplemento(), request.getBairro(), request.getCidade(), request.getNumero(), request.getUf());
        Usuario usuario = new Usuario(null, request.getNome(), request.getEmail(), request.getSenha(), 2, request.getFotoUsuario(), endereco);
        Usuario matriz = null;
        /*if (request.getMatrizId() != null && !request.getMatrizId().trim().isEmpty()) {
            var found = useCase.findByCnpj(request.getMatrizId().trim());
            if (found != null && found.isPresent()) {

                matriz = usuarioUseCase.buscarUsuario(found.get().getUsuario().getId());
            }
        }*/
        if (request.getMatrizId() != null && !request.getMatrizId().trim().isEmpty()) {
            Comercio come = useCase.findByCnpj(request.getMatrizId().trim()).get();
            matriz = come.getUsuario();
        }
        
        Comercio salvo = useCase.salvarComercioEntity(usuario, request.getCnpj(), request.getRazaoSocial(), request.getDescricao(), request.getSeguimento(), matriz);
        // gerar token para o usuário recém-criado
        String token = jwtUtil.generateToken(salvo.getUsuario().getEmail());
        ComercioWithTokenResponse resp = new ComercioWithTokenResponse(salvo, token);
        return ResponseEntity.ok(resp);
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

    @GetMapping("/top5/setor/{seguimento}")
    public ResponseEntity<List<Comercio>> buscarTop5PorSetor(@PathVariable String seguimento) {
        if (seguimento == null || seguimento.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (!seguimento.matches("[a-zA-ZÀ-ÿ0-9\\s\\-_]+")) {
            return ResponseEntity.badRequest().build();
        }
        
        List<Comercio> comercios = useCase.buscarTop5ComerciosPorSeguimento(seguimento.trim());
        return ResponseEntity.ok(comercios);
    }

    @GetMapping("/top5/setores-principais")
    public ResponseEntity<List<Comercio>> buscarTop5SetoresPrincipais() {
        List<Comercio> comercios = useCase.buscarTop5RestauranteFarmaciaOutros();
        return ResponseEntity.ok(comercios);
    }

    @GetMapping("/top5/cada-setor")
    public ResponseEntity<List<Comercio>> buscarTop5DeCadaSetor() {
        List<Comercio> comercios = useCase.buscarTop5DeCadaSetor();
        return ResponseEntity.ok(comercios);
    }

    @GetMapping("/top5/setores")
    public ResponseEntity<List<Comercio>> buscarTop5PorSetores(@RequestParam List<String> setores) {
        // Validação adicional para lista de setores
        if (setores == null || setores.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        // Validar cada setor da lista
        for (String setor : setores) {
            if (setor == null || setor.trim().isEmpty() || !setor.matches("[a-zA-ZÀ-ÿ0-9\\s\\-_]+")) {
                return ResponseEntity.badRequest().build();
            }
        }
        
        List<Comercio> comercios = useCase.buscarTop5ComerciosMultiplosSeguimentos(setores);
        return ResponseEntity.ok(comercios);
    }

}
