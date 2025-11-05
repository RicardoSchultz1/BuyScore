package com.senac.ProjetoPontos.Aplication.UseCase;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.senac.ProjetoPontos.Domain.Entity.Cliente;
import com.senac.ProjetoPontos.Domain.Entity.Comercio;
import com.senac.ProjetoPontos.Domain.Entity.Endereco;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;
import com.senac.ProjetoPontos.Domain.Exception.NaoEncontradoException;
import com.senac.ProjetoPontos.Domain.Repository.ClienteRepository;
import com.senac.ProjetoPontos.Domain.Repository.EnderecoRepository;
import com.senac.ProjetoPontos.Domain.Repository.UsuarioRepository;

@Service
public class ClienteUseCase {
    
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public ClienteUseCase(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Cliente salvarClienteEntity(Usuario usuario){
        try {
            Endereco iden = enderecoRepository.save(usuario.getEndereco());

            try {
            usuario.setEndereco(iden);   
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha())); 
            Usuario idus =usuarioRepository.save(usuario);

                try {
                    
                    Cliente cliente = new Cliente(null, idus, 0, null, null );
                    return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar cliente", e);
        }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar endereço", e);
        }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário", e);
        }
    } 

    public Cliente salvarCliente(Cliente cliente) {
        cliente.setUsuario(usuarioRepository.findById(cliente.getUsuario().getId())); 
        return clienteRepository.save(cliente);
    }

    public Cliente buscarCliente(UUID id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            throw new NaoEncontradoException("" + id);
        }
        return cliente;
    }

    public Cliente buscarClientePorUsuarioId(UUID usuarioId) {
        Cliente cliente = clienteRepository.findByUsuarioId(usuarioId);
        if (cliente == null) {
            throw new NaoEncontradoException("Cliente não encontrado para o usuário ID: " + usuarioId);
        }
        return cliente;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public void atualizarCliente(Cliente cliente) {
        clienteRepository.update(cliente);
    }
    public void deletarCliente(UUID id) {
        clienteRepository.delete(id);
    }

    public void adicionarComercioFavorito(UUID clienteId, UUID comercioId) {
        clienteRepository.adicionarComercioFavorito(clienteId, comercioId);
    }

    public void removerComercioFavorito(UUID clienteId, UUID comercioId) {
        clienteRepository.removerComercioFavorito(clienteId, comercioId);
    }

    public List<Comercio> listarComerciosFavoritos(UUID clienteId) {
        return clienteRepository.findComerciosFavoritos(clienteId);
    }

    public boolean isComercioFavorito(UUID clienteId, UUID comercioId) {
        return clienteRepository.isComercioFavorito(clienteId, comercioId);
    }
}
