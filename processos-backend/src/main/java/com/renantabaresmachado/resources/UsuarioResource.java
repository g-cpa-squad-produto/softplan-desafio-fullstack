package com.renantabaresmachado.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renantabaresmachado.domains.Usuario;
import com.renantabaresmachado.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Integer id) {		
		return ResponseEntity.ok().body(usuarioService.buscar(id));
	}

}
