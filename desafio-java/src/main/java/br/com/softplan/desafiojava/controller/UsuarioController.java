package br.com.softplan.desafiojava.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.desafiojava.entity.Usuario;

@RestController
public class UsuarioController {

	@GetMapping("/usuario")
	public List<Usuario> listarTodos() {
		return Arrays.asList(
				new Usuario(1L, "123", "Pablo"), 
				new Usuario(2L, "456", "Elaine"));
	}
	
	@GetMapping("/usuario/{id}")
	public Usuario consultar(@PathVariable Long id) {
		return new Usuario(1L, "123", "Pablo"); 
	}
	
	@PostMapping("/usuario")
	public Usuario incluir(@RequestBody Usuario usuario) {
		usuario.setId(1000L);
		return usuario; 
	}
	
	@PutMapping("/usuario")
	public Usuario alterar(@RequestBody Usuario usuario) {
		usuario.setMatricula(usuario.getMatricula() + "A");
		return usuario; 
	}
	
	@DeleteMapping("/usuario/{id}")
	public String excluir(@PathVariable Long id) {
		return "Usuario Excluido com sucesso"; 
	}
	
}
