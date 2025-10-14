package com.senac.ProjetoPontos.InterfaceAdapters.Controllers;

import org.springframework.web.bind.annotation.*;

import com.senac.ProjetoPontos.Aplication.UseCase.CepUseCase;
import com.senac.ProjetoPontos.Aplication.UseCase.EnderecoUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Endereco;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoUseCase enderecoService;
    private final CepUseCase cepService;
    
    public EnderecoController(EnderecoUseCase enderecoService, CepUseCase cepService) {
        this.enderecoService = enderecoService;
        this.cepService = cepService;
    }
    
    @PostMapping
    public Endereco criarEndereco(@RequestBody Endereco endereco) {
        return enderecoService.salvarEnderecoRepository(endereco);
    }
    
    @GetMapping("/{id}")
    public Endereco buscarEndereco(@PathVariable java.util.UUID id) {
        return enderecoService.buscarEnderecoRepository(id);
    }

    @DeleteMapping("/{id}")
    public void deletarEndereco(@PathVariable java.util.UUID id) {
        enderecoService.deletarEnderecoRepository(id);
    }

    @PutMapping
    public void atualizarEndereco(@RequestBody Endereco endereco) {
        enderecoService.atualizarEnderecoRepository(endereco);
    }

    @GetMapping("/all")
    public java.util.List<Endereco> listarEnderecos() {
        return enderecoService.listarEnderecosRepository();
    }

    @PostMapping("/cep")
    public Endereco buscarCep(@RequestBody String cep) {
        Endereco endereco = cepService.buscarPorCep(cep);
        return endereco; 
    }
}
