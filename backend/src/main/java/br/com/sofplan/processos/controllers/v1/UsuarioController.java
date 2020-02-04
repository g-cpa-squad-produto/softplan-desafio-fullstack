package br.com.sofplan.processos.controllers.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sofplan.processos.dto.v1.CreateUsuarioDTO;
import br.com.sofplan.processos.dto.v1.UsuarioDTO;
import br.com.sofplan.processos.services.UsuarioService;
import io.swagger.annotations.Api;

@Api(tags = { "Asuarios" })
@RestController
@RequestMapping("/api/v1/usuarios")
@PreAuthorize("hasAuthority('ADMIN')")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public List<UsuarioDTO> find() {
		return usuarioService.find();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UsuarioDTO create(@RequestBody @Valid CreateUsuarioDTO request) {
		return usuarioService.create(request);
	}

	@PutMapping("/{id}")
	public UsuarioDTO update(@PathVariable Long id, @RequestBody @Valid UsuarioDTO request) {
		return usuarioService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		usuarioService.delete(id);
	}

}
