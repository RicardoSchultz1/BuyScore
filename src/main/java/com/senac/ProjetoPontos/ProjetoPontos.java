package com.senac.ProjetoPontos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.senac.ProjetoPontos.Aplication.UseCase.UsuarioUseCase;



@SpringBootApplication
public class ProjetoPontos {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPontos.class, args);

	}

	/*@Bean
    public CommandLineRunner initUsuario(UsuarioUseCase usuarioUseCase) {
        return args -> {
            // Criar usuário com senha simples - o UsuarioUseCase vai criptografar
            String nome = "Ricardo Adm";
            String email = "schul.dauer@gmail.com";
            String senha = "suasenha"; // Senha simples - será criptografada automaticamente
            int tipo = 1;
            String imagem = "https://i.imgur.com/0y0y0y0.png";

            // Verifica se o usuário já existe antes de criar
            try {
                usuarioUseCase.salvarUsuarioEntity(nome, email, senha, tipo, imagem, null);
                System.out.println("Usuário de teste criado: " + email);
            } catch (IllegalArgumentException e) {
                System.out.println("Usuário já existe: " + email);
            }
        };  
    } */
}