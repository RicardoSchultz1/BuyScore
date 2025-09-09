package com.senac.ProjetoPontos.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.ProjetoPontos.Model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID>{
	
	@Query(nativeQuery = true, value = "Select * from usuario where email_usuario = :email_usuario")
    Optional<UsuarioModel> findByEmail (String email_usuario);

	@Query(nativeQuery = true, value = "Select * from usuario where id = :id")
    Optional<UsuarioModel> findById (int id);
}
