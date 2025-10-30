package com.senac.ProjetoPontos.Aplication.UseCase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Endereco;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ComercioRepository;
import com.senac.ProjetoPontos.Domain.Repository.EnderecoRepository;

import com.senac.ProjetoPontos.Domain.Repository.UsuarioRepository;

@Service
public class ComercioUseCase {
    
    private final ComercioRepository comercioRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;


    public ComercioUseCase(ComercioRepository comercioRepository, UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository) {
        this.comercioRepository = comercioRepository;
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Comercio salvarComercioEntity(Usuario usuario, String cnpj, String razaoSocial, String descricao, String seguimento, Usuario matriz) {
        if (cnpj != null && comercioRepository.findByCnpj(cnpj).isPresent()) {
            throw new IllegalArgumentException("Cnpj já cadastrado");
        }
        if (usuario.getPerfilUsuario() != 2) {
            throw new IllegalArgumentException("Usuário não possui perfil de comércio");
        }
        try {
            Endereco iden = enderecoRepository.save(usuario.getEndereco());

            try {
            usuario.setEndereco(iden);
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            Usuario idus =usuarioRepository.save(usuario);

                try {
                    Comercio comercio = new Comercio(null, idus, cnpj, razaoSocial, descricao, seguimento, matriz, 0);
                    return comercioRepository.save(comercio);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar comércio", e);
        }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar endereço", e);
        }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário", e);
        }
        
    }
    public Comercio salvarComercioUser(Comercio comercio, Usuario usuario) {
        if (comercioRepository.existsByUsuarioId(comercio.getUsuario().getId())) {
            throw new IllegalArgumentException("Usuário já possui comércio");
        }
        if (comercio.getUsuario().getPerfilUsuario() != 2) {
            throw new IllegalArgumentException("Usuário não possui perfil de comércio");
        }
        
        comercio.setId(UUID.randomUUID());
        comercio.setUsuario(usuarioRepository.findById(comercio.getUsuario().getId())); 
        return comercioRepository.save(comercio);
    }

    public Comercio salvarComercio(Comercio comercio) {
        comercio.setUsuario(usuarioRepository.findById(comercio.getUsuario().getId())); 
        return comercioRepository.save(comercio);
    }

    public Comercio buscarComercio(UUID id) {
        Comercio comercio = comercioRepository.findById(id);
        if (comercio == null) {
            throw new NaoEncontradoException("" + id);
        }
        return comercio;
    }

    public List<Comercio> findAll() {
        return comercioRepository.findAll();
    }

    public Optional<Comercio> findByCnpj(String cnpj) {
        return comercioRepository.findByCnpj(cnpj);
    }

    public void atualizarComercio(Comercio comercio) {
        comercioRepository.update(comercio);
    }
    public void deletarComercio(UUID id) {
        comercioRepository.delete(id);
    }

    public Comercio buscarComercioPorUsuarioId(UUID usuarioId) {
        return comercioRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new NaoEncontradoException("Comércio não encontrado para o usuário ID: " + usuarioId));
    }

    // Métodos para consultas personalizadas de vendas
    public List<Comercio> buscarTop5ComerciosPorSeguimento(String seguimento) {
        List<Comercio> resultado = comercioRepository.findTop5BySeguimento(seguimento);
        return resultado;
    }

    public List<Comercio> buscarTop5ComerciosMultiplosSeguimentos(List<String> seguimentos) {
        return comercioRepository.findTop5BySeguimentos(seguimentos);
    }

    public List<Comercio> buscarTop5DeCadaSetor() {
        return comercioRepository.findTop5FromEachSector();
    }

    // Método específico para buscar os top 5 dos 3 setores mencionados
    public List<Comercio> buscarTop5RestauranteFarmaciaOutros() {
        List<String> setores = List.of("restaurante", "farmacia", "outros");
        return comercioRepository.findTop5BySeguimentos(setores);
    }

}
