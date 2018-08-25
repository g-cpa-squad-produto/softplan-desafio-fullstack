/**
 * 
 */
package br.com.desafiofullstack.resource;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiofullstack.domain.Usuario;
import br.com.desafiofullstack.service.UsuarioService;

/**
 * @author Delano Jr
 *
 */
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	private ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.ok(usuarioService.save(usuario).orElse(null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	private ResponseEntity<?> findAll() {
		try {
			return ResponseEntity.ok(usuarioService.findAll().orElse(null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(value = "/{isnUsuario}")
	private ResponseEntity<?> findOneUsuario(@PathVariable("isnUsuario") Long isnUsuario) {
		try {
			return ResponseEntity.ok(usuarioService.findOne(isnUsuario).orElse(null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
