package com.senac.ProjetoPontos.Domain.Exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String id) {
        super("Usuário não encontrado com id: " + id);
    }
}

