package com.softplan.thiagobernardo.parecer.controller;

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

import com.softplan.thiagobernardo.login.controller.LoginApiController;
import com.softplan.thiagobernardo.parecer.entity.Parecer;
import com.softplan.thiagobernardo.parecer.service.ParecerService;
import com.softplan.thiagobernardo.usuario.entity.UsuarioDTO;
import com.softplan.thiagobernardo.usuario.service.UsuarioService;

@RestController
public class ParecerApiController {
	
	
	@Autowired
	private ParecerService parecerService;
	
	@Autowired
	private UsuarioService usuarioService;	

	@GetMapping("pareceres")
	public List<Parecer> listar(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			return parecerService.listar();
		}
		return null;
	}
	
	@GetMapping("pareceres/{parecerId}")
	public Parecer trazer(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long parecerId) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			return parecerService.trazer(parecerId);
		}
		return null;
	}
	
	@PostMapping("pareceres")
	public Parecer criar(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @Valid @RequestBody Parecer parecer) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			return parecerService.criar(parecer);
		}
		return null;
	}

	@PutMapping("pareceres/{parecerId}")
	public Parecer alterar(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long parecerId, @Valid @RequestBody Parecer parecerRequest) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			return parecerService.alterar(parecerId, parecerRequest);
		}
		return null;
	}

	@DeleteMapping("/pareceres/{parecerId}")
	public void deletar(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long parecerId) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			parecerService.deletar(parecerId);
		}
	}
	
	@PostMapping("pareceres/processo")
	public Parecer criarParecerProcesso(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @Valid @RequestBody Parecer parecer) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			return parecerService.salvarParecerProcesso(parecer);
		}
		return null;
	}

}
