package br.com.ramonbarros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ramonbarros.entity.Usuario;
import br.com.ramonbarros.service.UsuarioService;

@RestController
@RequestMapping("${usuario.servlet.path}")
public class UsuarioController {
	
	@Autowired
	UsuarioService service;
	
	@GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        final List<Usuario> lista = service.listar();
        return ResponseEntity.ok().body(lista);
    }
	
	@GetMapping(value="/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        final Usuario usuario = service.buscarPorId(id);
        return ResponseEntity.ok().body(usuario);
    }

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Usuario usuario){
		service.salvar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody Usuario usuario, @PathVariable Long id) {
		usuario.setId(id);
		usuario = service.alterar(usuario);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
