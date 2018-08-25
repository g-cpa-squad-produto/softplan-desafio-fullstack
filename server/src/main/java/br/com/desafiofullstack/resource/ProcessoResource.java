package br.com.desafiofullstack.resource;

import java.io.Serializable;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiofullstack.domain.Processo;
import br.com.desafiofullstack.service.ProcessoService;

@RestController
@RequestMapping(value = "/processo")
public class ProcessoResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProcessoService processoService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON)
	private ResponseEntity<?> saveProcesso(@RequestBody Processo processo) {
		try {
			return ResponseEntity.ok(processoService.save(processo).orElse(null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	private ResponseEntity<?> findAll() {
		try {
			return ResponseEntity.ok(processoService.findAll().orElse(null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
