package com.softplan.thiagobernardo.processo.controller;

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

import com.softplan.thiagobernardo.processo.entity.Processo;
import com.softplan.thiagobernardo.processo.repository.ProcessoRepository;
import com.softplan.thiagobernardo.util.ParecerStatus;

@RestController
public class ProcessoApiController {
	
	@Autowired
	private ProcessoRepository processoRepository;


	@GetMapping("processos")
	public List<Processo> listarProcessos() {
		return processoRepository.findAll();
	}
	
	@GetMapping("processos/{processoId}")
	public Processo listarProcessos(@PathVariable Long processoId) {
		return processoRepository.findById(processoId).orElseThrow(() -> new RuntimeException("Processo não encontrado!"));
	}
	
	@PostMapping("processos")
	public Processo criarProcesso(@Valid @RequestBody Processo processo) {
		return processoRepository.save(processo);
	}

	@PutMapping("processos/{processoId}")
	public Processo alterarProcesso(@PathVariable Long processoId, @RequestBody Processo processoRequest) {
		return processoRepository.findById(processoId).map(processo -> {
			processo.setDescricao(processoRequest.getDescricao());
			processo.setUsuariosPararecer(processoRequest.getUsuariosPararecer());
			return processoRepository.save(processo);
		}).orElseThrow(() -> new RuntimeException("Processo não encontrado!"));
	}

	@DeleteMapping("/processos/{processoId}")
	public ResponseEntity<?> deletarProcesso(@PathVariable Long processoId) {
		if (!processoRepository.existsById(processoId)) {
			throw new RuntimeException("Processo não encontrado!");
		}

		return processoRepository.findById(processoId).map(processo -> {
			processoRepository.delete(processo);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new RuntimeException("Processo não encontrado!"));
	}
	
	@GetMapping("processos/usuarios/{processoId}")
	public List<Processo> listarProcessos22(@PathVariable Long processoId) {
		return processoRepository.findByStatusParecerAndUsuariosPararecer_id(ParecerStatus.PENDENTE,processoId);
	}
	
	
	
}
