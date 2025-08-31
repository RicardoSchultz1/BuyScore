package com.senac.ProjetoPontos.controller;
/*
package com.senac.CondoConnect.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.senac.CondoConnect.Model.UsuarioModel;
import com.senac.CondoConnect.dtos.UsuarioRecord;
import com.senac.CondoConnect.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins  = "*")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioservice;
	
	@PostMapping(value ="/newusuario") //retorna 201
	public ResponseEntity<Object> savePost(@RequestBody @Valid UsuarioRecord usuariodto) {
		
		var usermodel = new UsuarioModel();
		BeanUtils.copyProperties(usuariodto, usermodel);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioservice.save(usermodel));
	}
	@GetMapping(value = "/usuario")
	public ResponseEntity<List<UsuarioModel>> getPosts(){
		List<UsuarioModel> user = usuarioservice.findAll();
		
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
			
		}
			return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	@GetMapping(value ="/usuario/{id}")
	public ResponseEntity<Object> getUsuarioDetails(@PathVariable("id") int id) {
		Optional<UsuarioModel> usuario = usuarioservice.findById(id);
		
		if(!usuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario not found.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
	}
	
	@DeleteMapping(value = "/deleteusuario/{id}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable("id") int id ){
		Optional<UsuarioModel> blogappModelOptional = usuarioservice.findById(id);
		
		if(!blogappModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario not found.");
		}
		usuarioservice.delete(blogappModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Deleted sucefully");
	}
	
	@PutMapping(value ="/putusuario/{id}")
	public ResponseEntity<Object> putUsuario(@RequestBody @Valid UsuarioRecord usuariodto,@PathVariable("id") int id){
		Optional<UsuarioModel> blogappModelOptional = usuarioservice.findById(id);

		if(!blogappModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario not found.");
		}
		var usuarioModel = new UsuarioModel();
		BeanUtils.copyProperties(usuariodto, usuarioModel);
		usuarioModel.setId(blogappModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioservice.save(usuarioModel));
	}
	@GetMapping(value ="/validacadastro/{email}/{senha}")
	public ResponseEntity<Object> getCadastro(@PathVariable("email") String email, @PathVariable("senha") String senha) {
		Optional<UsuarioModel> blogappModelOptional = usuarioservice.findByEmail(email);
		
		if (!blogappModelOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
		}	
		if (blogappModelOptional.isPresent()) {
			
			if(senha.equals(blogappModelOptional.get().getSenhaUsuario())) {
				return ResponseEntity.status(HttpStatus.OK).body(blogappModelOptional.get().getId());
			}
			if(!senha.equals(blogappModelOptional.get().getSenhaUsuario())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
			}
		} 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
		
	}
	
}
	*/
