package br.com.softplan.desafiojava.controller;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.desafiojava.entity.Usuario;
import br.com.softplan.desafiojava.repository.UsuarioJpaRepository;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioJpaRepository repository;
	
	@GetMapping("/usuario")
	public List<Usuario> listarTodos() {
		return repository.findAll();
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> consultar(@PathVariable Long id) {
		Optional<Usuario> op = repository.findById(id);
		if (!op.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(op.get(), HttpStatus.OK);
	}
	
	@PostMapping("/usuario")
	public Usuario incluir(@RequestBody Usuario usuario) {
		return repository.save(usuario); 
	}
	
	@PutMapping("/usuario")
	public Usuario alterar(@RequestBody Usuario usuario) {
		return repository.save(usuario); 
	}
	
	@DeleteMapping("/usuario/{id}")
	public String excluir(@PathVariable Long id) {
		repository.deleteById(id);
		return "Usuario Excluido com sucesso"; 
	}
	
}
