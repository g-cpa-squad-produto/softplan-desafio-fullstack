package br.com.softplan.backend.usuario;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

	@PutMapping("/usuario/{usuarioId}")
	public ResponseEntity editUsuario(@RequestBody UsuarioModel usuarioModel, @PathVariable String usuarioId){
		usuarioModel.setUsuarioId(usuarioId);
		usuarioService.updateUsuario(usuarioModel);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/usuario/{usuarioId}")
	public ResponseEntity deleteUsuario(@PathVariable String usuarioId){
		usuarioService.deleteUsuario(usuarioId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/usuarios")
	public List<UsuarioModel> getAllUsuario() {
		return usuarioService.findAll();
	}

	@GetMapping("/usuario/{usuarioId}")
	public Optional<UsuarioModel> getUsuarioById(@PathVariable String usuarioId) {
		return usuarioService.findById(usuarioId);
	}
}
