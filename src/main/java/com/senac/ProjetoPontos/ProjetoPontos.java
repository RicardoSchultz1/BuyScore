package com.senac.ProjetoPontos;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.senac.ProjetoPontos.Aplication.UseCase.UsuarioUseCase;
import com.senac.ProjetoPontos.Domain.Entity.Usuario;



@SpringBootApplication
public class ProjetoPontos {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPontos.class, args);

	}

	/*@Bean
    public CommandLineRunner initUsuario(UsuarioUseCase usuarioUseCase) {
        return args -> {
            String encodedPassword = new BCryptPasswordEncoder().encode("suasenha");
            System.out.println("Senha criptografada: " + encodedPassword);

            UUID usuarioId = UUID.randomUUID();
            String nome = "Ricardo Adm";
            String email = "schultz.dauer@gmail.com";
            String senha = encodedPassword;
            int tipo = 1;
            String imagem = "https://i.imgur.com/0y0y0y0.png";
            UUID algumUUID = null;

            usuarioUseCase.salvarUsuarioEntity(nome, email, senha, tipo, imagem, usuarioId);
        };  */
    }
