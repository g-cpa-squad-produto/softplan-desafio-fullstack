package br.com.softplan.desafiojava.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.desafiojava.entity.Parecer;
import br.com.softplan.desafiojava.repository.ParecerJpaRepository;

@RestController
public class ParecerController {
	
	@Autowired
	ParecerJpaRepository repository;

	@GetMapping("/parecer/{id}")
	public ResponseEntity<Parecer> consultar(@PathVariable Long id) {
		Optional<Parecer> op = repository.findById(id);
		if (!op.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Parecer>(op.get(), HttpStatus.OK);
	}
	
	/**
	 * Pareceres de um determinado processo
	 * @param id Identificador do processo
	 * @return Lista de pareceres
	 */
	@GetMapping("/parecer/processo/{id}")
	public List<Parecer> listarPorProcesso(@PathVariable Long id) {
		return repository.findByProcesso(id);
	}

	@PostMapping("/parecer")
	public Parecer incluir(@RequestBody Parecer parecer) {
		return repository.save(parecer);
	}
}
