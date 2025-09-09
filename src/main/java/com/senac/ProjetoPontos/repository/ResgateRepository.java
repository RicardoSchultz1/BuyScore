package com.senac.ProjetoPontos.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.ProjetoPontos.Model.ResgateModel;


public interface ResgateRepository extends JpaRepository<ResgateModel, UUID>{

	@Query(nativeQuery = true, value = "Select * from resgate where id = :id")
    Optional<ResgateModel> findById (int id);
    
}
