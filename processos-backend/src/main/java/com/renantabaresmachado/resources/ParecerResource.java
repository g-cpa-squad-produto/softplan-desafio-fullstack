package com.renantabaresmachado.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renantabaresmachado.domains.Parecer;
import com.renantabaresmachado.services.ParecerService;

@RestController
@RequestMapping(value = "/pareceres")
public class ParecerResource {
	
	@Autowired
	private ParecerService parecerService;
	
	@PreAuthorize("hasAnyRole('FINALIZADOR')")
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody Parecer parecer) {
		parecer = parecerService.inserir(parecer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(parecer.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
