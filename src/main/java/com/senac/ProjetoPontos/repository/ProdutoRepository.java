package com.senac.ProjetoPontos.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.ProjetoPontos.Model.ProdutoModel;


public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID>{
	
	@Query(nativeQuery = true, value = "Select * from produto where id = :id")
    Optional<ProdutoModel> findById (int id);

    @Query(nativeQuery = true, value = "Select * from produto where usuario_juridico_id = :usuario_juridico_id")
    List<ProdutoModel> findByUsuarioJuridicoId (int usuario_juridico_id);
}
