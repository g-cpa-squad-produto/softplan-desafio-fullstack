package br.com.softplan.jean.processo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.jean.login.controller.LoginApiController;
import br.com.softplan.jean.processo.entity.Processo;
import br.com.softplan.jean.processo.entity.ProcessoDTO;
import br.com.softplan.jean.processo.service.ProcessoService;
import br.com.softplan.jean.usuario.entity.UsuarioDTO;
import br.com.softplan.jean.usuario.service.UsuarioService;

@RestController
public class ProcessoApiController {
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("processos")
	public List<ProcessoDTO> listarProcessos(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isTriador()) {
			return processoService.listar();
		}
		return null;
	}
	
	@GetMapping("processos/{processoId}")
	public ProcessoDTO trazer(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long processoId) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isTriador() || usuario.isFinalizador()) {
			return processoService.trazer(processoId);
		}
		return null;	
	}
	
	@PostMapping("processos")
	public Processo criarProcesso(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @Valid @RequestBody Processo processo) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isTriador()) {
			return processoService.criar(processo);
		}
		return null;	
	}

	@PutMapping("processos/{processoId}")
	public Processo alterarProcesso(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long processoId, @RequestBody Processo processoRequest) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isTriador()) {
			return processoService.alterar(processoId, processoRequest);
		}
		return null;
	}

	@DeleteMapping("/processos/{processoId}")
	public void deletarProcesso(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long processoId) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isTriador()) {
			processoService.deletar(processoId);
		}
	}
	
	@GetMapping("processos/usuarios/pendentes")
	public List<ProcessoDTO> listarPendentesDeParecerPorUsuario(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			return processoService.listarPendentesDeParecerPorUsuario(usuario.getId());
		}
		return null;
	}
	
}
