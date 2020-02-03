package br.com.sofplan.processos.controllers.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sofplan.processos.dto.v1.ProcessoDTO;
import br.com.sofplan.processos.services.ProcessoService;
import io.swagger.annotations.Api;

@Api(tags = { "Processos" })
@RestController
@RequestMapping("/api/v1/processos")
public class ProcessoController {

	private final ProcessoService processoService;

	public ProcessoController(ProcessoService processoService) {
		this.processoService = processoService;
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
	public ProcessoDTO create(@RequestBody @Valid ProcessoDTO request) {
		return processoService.create(request);
	}

	@PutMapping("/{id}")
	public ProcessoDTO update(@PathVariable Long id, @RequestBody @Valid ProcessoDTO request) {
		return processoService.update(id, request);
	}

	@DeleteMapping("/{id}")
	public ProcessoDTO delete(@PathVariable Long id) {
		return processoService.delete(id);
	}

	@PostMapping("/{processoId}/responsaveis/{usuarioId}")
	public void addResponsavel(@PathVariable Long processoId, @PathVariable Long usuarioId) {
		processoService.addResponsavel(processoId, usuarioId);
	}

	@DeleteMapping("/{processoId}/responsaveis/{usuarioId}")
	public void deleteResponsavel(@PathVariable Long processoId, @PathVariable Long usuarioId) {
		processoService.deleteResponsavel(processoId, usuarioId);
	}
}
