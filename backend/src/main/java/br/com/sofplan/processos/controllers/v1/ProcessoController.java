package br.com.sofplan.processos.controllers.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sofplan.processos.dto.v1.ParecerDTO;
import br.com.sofplan.processos.dto.v1.ProcessoDTO;
import br.com.sofplan.processos.services.ParecerService;
import br.com.sofplan.processos.services.ProcessoService;
import io.swagger.annotations.Api;

@Api(tags = { "Processos" })
@RestController
@RequestMapping("/api/v1/processos")
public class ProcessoController {

	private final ProcessoService processoService;
	private final ParecerService parecerService;

	public ProcessoController(ProcessoService processoService, ParecerService parecerService) {
		this.processoService = processoService;
		this.parecerService = parecerService;
	}

	@GetMapping
	public List<ProcessoDTO> find() {
		return processoService.find();
	}

	@GetMapping("/{id}")
	public ProcessoDTO findById(@PathVariable Long id) {
		return processoService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProcessoDTO create(@RequestBody @Valid ProcessoDTO request) {
		return processoService.create(request);
	}

	@PutMapping("/{id}")
	public ProcessoDTO update(@PathVariable Long id, @RequestBody @Valid ProcessoDTO request) {
		return processoService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		processoService.delete(id);
	}

	@PostMapping("/{id}/responsaveis/{usuarioId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addResponsavel(@PathVariable("id") Long processoId, @PathVariable Long usuarioId) {
		processoService.addResponsavel(processoId, usuarioId);
	}

	@DeleteMapping("/{id}/responsaveis/{usuarioId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteResponsavel(@PathVariable("id") Long processoId, @PathVariable Long usuarioId) {
		processoService.deleteResponsavel(processoId, usuarioId);
	}
	
	@GetMapping("/{id}/parecer")
	public ParecerDTO findParecer(@PathVariable Long id) {
		return parecerService.findById(id);
	}
	
	@PostMapping("/{id}/parecer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ParecerDTO createParecer(@PathVariable Long id, ParecerDTO request) {
		request.setId(id);
		return parecerService.create(request);
	}

	@PutMapping("/{id}/parecer")
	public ParecerDTO updateParecer(@PathVariable Long id, ParecerDTO request) {
		return parecerService.update(id, request);
	}

	@DeleteMapping("/{id}/parecer")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteParecer(@PathVariable Long id) {
		parecerService.delete(id);
	}
	
}
