package com.senac.ProjetoPontos.Infrastructure.Persistence.Endereco;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="Endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

    protected EnderecoEntity() {}

    public EnderecoEntity(UUID id) {
        this.id = id;
    }
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
}
