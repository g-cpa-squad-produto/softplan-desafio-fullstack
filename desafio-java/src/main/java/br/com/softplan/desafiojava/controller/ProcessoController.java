package br.com.softplan.desafiojava.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.desafiojava.entity.Processo;
import br.com.softplan.desafiojava.entity.Usuario;
import br.com.softplan.desafiojava.repository.ProcessoJpaRepository;

@RestController
public class ProcessoController {

	@Autowired
	ProcessoJpaRepository repository;
	
	@GetMapping("/processo")
	public List<Processo> listarTodos() {
		return repository.findAll();
	}
	
	@GetMapping("/processo/{id}")
	public ResponseEntity<Processo> consultar(@PathVariable Long id) {
		Optional<Processo> op = repository.findById(id);
		if (!op.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Processo>(op.get(), HttpStatus.OK);
	}
	
	/**
	 * Processos pendentes de um usuário
	 * @param id Identificador do usuário que está solicitando a lista
	 * @return Lista de processos
	 */
	@GetMapping("/processo/usuario/{id}")
	public List<Processo> listarPorUsuario(@PathVariable Long id) {
		return repository.findByUsuario(id);
	}
	
	@PostMapping("/processo")
	public void incluir(@RequestBody Processo processo) {
		repository.save(processo);
	}
	
	/**
	 * Associa o(s) usuário(s) ao processo informado 
	 * @param listaUsuarios Lista de usuários a serem associados
	 * @param id Identificador do processo.
	 */
	@PatchMapping("/processo/{id}")
	public ResponseEntity<Processo> associarUsuarios(@RequestBody List<Usuario> listaUsuarios, @PathVariable Long id) {
		Optional<Processo> op = repository.findById(id);
		if (!op.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Processo p = op.get();
		p.setFinalizadores(listaUsuarios);
		repository.save(p);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
}
