package br.com.softplan.backend.triador.controller;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.backend.administrador.model.UsuarioModel;
import br.com.softplan.backend.administrador.request.UsuarioRequest;
import br.com.softplan.backend.triador.model.ProcessoModel;
import br.com.softplan.backend.triador.request.ProcessoRequest;
import br.com.softplan.backend.triador.service.ProcessoService;

@RestController
@Slf4j
public class ProcessoController {

	private final ProcessoService processoService;

	public ProcessoController(ProcessoService processoService) {
		this.processoService = processoService;
	}

	@PostMapping("/processo")
	public ResponseEntity addProcesso(@RequestBody ProcessoRequest processoRequest) {
		log.info("addProcesso request : {}", processoRequest);
		processoService.saveProcesso(processoRequest.toProcessoModel());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/processos")
	public List<ProcessoModel> getAllProcesso() {
		return processoService.findAll();
	}

	@GetMapping("/processo/{processoId}")
	public Optional<ProcessoModel> getProcessoById(@PathVariable String processoId) {
		return processoService.findById(processoId);
	}
}
