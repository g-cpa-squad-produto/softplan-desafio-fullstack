package com.softplan.thiagobernardo.parecer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.thiagobernardo.parecer.entity.Parecer;
import com.softplan.thiagobernardo.parecer.repository.ParecerRepository;

@RestController
public class ParecerApiController {
	
	
	@Autowired
	private ParecerRepository parecerRepository;	

	@GetMapping("pareceres")
	public List<Parecer> listarPareceres() {
		return parecerRepository.findAll();
	}
	
	@GetMapping("pareceres/{parecerId}")
	public Parecer listarPareceres(@PathVariable Long parecerId) {
		return parecerRepository.findById(parecerId).orElseThrow(() -> new RuntimeException("Parecer não encontrado!"));
	}
	
	@PostMapping("pareceres")
	public Parecer criarParecer(@Valid @RequestBody Parecer parecer) {
		return parecerRepository.save(parecer);
	}

	@PutMapping("pareceres/{parecerId}")
	public Parecer alterarParecer(@PathVariable Long parecerId, @Valid @RequestBody Parecer parecerRequest) {
		return parecerRepository.findById(parecerId).map(parecer -> {
			parecer.setTitulo(parecerRequest.getTitulo());
			parecer.setDescricao(parecerRequest.getDescricao());
			return parecerRepository.save(parecer);
		}).orElseThrow(() -> new RuntimeException("Parecer não encontrado!"));
	}

	@DeleteMapping("/pareceres/{parecerId}")
	public ResponseEntity<?> deletarParecer(@PathVariable Long parecerId) {
		if (!parecerRepository.existsById(parecerId)) {
			throw new RuntimeException("Parecer não encontrado!");
		}

		return parecerRepository.findById(parecerId).map(parecer -> {
			parecerRepository.delete(parecer);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new RuntimeException("Parecer não encontrado!"));
	}
	
	
	//
	@GetMapping("pareceres/processo/{processoId}")
	public List<Parecer> listarPareceresPorPorcesso(@PathVariable Long processoId) {
		return parecerRepository.findByProcessoId(processoId);
	}

}
