package br.com.softplan.backend.administrador.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.backend.administrador.model.UsuarioModel;
import br.com.softplan.backend.administrador.request.UsuarioRequest;
import br.com.softplan.backend.administrador.service.UsuarioService;

@RestController
@Slf4j
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/usuario")
	public ResponseEntity addUsuario(@RequestBody UsuarioRequest usuarioRequest) {
		log.info("addUsuario request : {}", usuarioRequest);
		usuarioService.saveUsuario(usuarioRequest.toUsuarioModel());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/usuarios")
	public List<UsuarioModel> getAllUsuario() {
		return usuarioService.findAll();
	}
}
