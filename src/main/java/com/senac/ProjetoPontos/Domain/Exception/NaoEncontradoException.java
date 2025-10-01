package com.senac.ProjetoPontos.Domain.Exception;

public class NaoEncontradoException extends RuntimeException {
    public NaoEncontradoException(String id) {
        super("Objeto não encontrado com id: " + id);
    }
}

