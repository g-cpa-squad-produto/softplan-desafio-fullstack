package com.renantabaresmachado.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renantabaresmachado.domains.Processo;
import com.renantabaresmachado.security.UserSSecurity;
import com.renantabaresmachado.services.ProcessoService;
import com.renantabaresmachado.services.UserService;
import com.renantabaresmachado.services.exeptions.AuthorizationException;

@RestController
@RequestMapping(value = "/processos")
public class ProcessoResource {
	
	@Autowired
	private ProcessoService processoService;
	
	@PreAuthorize("hasAnyRole('TRIADOR')")
	@GetMapping(value ="/{id}")
	public ResponseEntity<Processo> buscar(@PathVariable Integer id) {		
		return ResponseEntity.ok().body(processoService.buscar(id));
	}
	
	@PreAuthorize("hasAnyRole('TRIADOR')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Processo processo) {
		processo = processoService.inserir(processo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(processo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('TRIADOR')")
	@GetMapping
	public ResponseEntity<List<Processo>> buscarTodos() {
		List<Processo> listProcessos= processoService.buscarTodos();
		return ResponseEntity.ok().body(listProcessos);
	}
	
	@PreAuthorize("hasAnyRole('FINALIZADOR')")
	@GetMapping(value = "/finalizador")
	public UserSSecurity buscarPorUsuarioFinalizador() {
		UserSSecurity user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		return user;
	}
	
}
