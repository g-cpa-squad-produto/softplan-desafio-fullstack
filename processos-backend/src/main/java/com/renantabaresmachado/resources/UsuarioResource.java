package com.renantabaresmachado.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renantabaresmachado.domains.Usuario;
import com.renantabaresmachado.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value ="/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Integer id) {		
		return ResponseEntity.ok().body(usuarioService.buscar(id));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Usuario usuario) {
		usuario = usuarioService.inserir(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Usuario usuario, @PathVariable Integer id) {
		usuario.setId(id);
		usuario = usuarioService.editar(usuario);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodos() {
		List<Usuario> listUsuarios = usuarioService.buscarTodos();
		return ResponseEntity.ok().body(listUsuarios);
	}



}
