package br.com.softplan.desafiojava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.desafiojava.business.UsuarioBusiness;
import br.com.softplan.desafiojava.entity.Usuario;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioBusiness business;
	
	@GetMapping("/usuario")
	public List<Usuario> listarTodos() {
		return business.listarTodos();
	}
	
	@GetMapping("/usuario/{id}")
	public Usuario consultar(@PathVariable Long id) {
		return business.consultar(id);
	}
	
	@PostMapping("/usuario")
	public Usuario incluir(@RequestBody Usuario usuario) {
		return business.incluir(usuario); 
	}
	
	@PutMapping("/usuario")
	public Usuario alterar(@RequestBody Usuario usuario) {
		return business.alterar(usuario); 
	}
	
	@DeleteMapping("/usuario/{id}")
	public void excluir(@PathVariable Long id) {
		business.excluir(id);
	}
	
}
