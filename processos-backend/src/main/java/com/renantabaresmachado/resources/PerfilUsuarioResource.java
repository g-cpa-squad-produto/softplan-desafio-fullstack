package com.renantabaresmachado.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renantabaresmachado.domains.PerfilUsuario;
import com.renantabaresmachado.services.PerfilUsuarioService;

@RestController
@RequestMapping(value = "/perfilusuarios")
public class PerfilUsuarioResource {

	@Autowired
	private PerfilUsuarioService perfilUsuarioService;
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody PerfilUsuario perfilUsuario) {
		perfilUsuario = perfilUsuarioService.inserir(perfilUsuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(perfilUsuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody PerfilUsuario perfilUsuario, @PathVariable Integer id) {
		perfilUsuario.setId(id);
		perfilUsuario = perfilUsuarioService.editar(perfilUsuario);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		perfilUsuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<PerfilUsuario>> buscarTodos() {
		List<PerfilUsuario> listPerfilUsuarios = perfilUsuarioService.buscarTodos();
		return ResponseEntity.ok().body(listPerfilUsuarios);
	}
}
