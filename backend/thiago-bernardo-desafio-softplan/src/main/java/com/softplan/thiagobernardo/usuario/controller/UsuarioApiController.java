package com.softplan.thiagobernardo.usuario.controller;

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

import com.softplan.thiagobernardo.exception.AcessoNaoPermitidoException;
import com.softplan.thiagobernardo.login.controller.LoginApiController;
import com.softplan.thiagobernardo.usuario.entity.Usuario;
import com.softplan.thiagobernardo.usuario.entity.UsuarioDTO;
import com.softplan.thiagobernardo.usuario.service.UsuarioService;

@RestController
public class UsuarioApiController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("usuarios")
	public List<UsuarioDTO> listarUsuarios(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isAdmin()) {
			return usuarioService.listar();
		}else {
			throw new AcessoNaoPermitidoException();
		}
	}
	
	@GetMapping("usuarios/{usuarioId}/")
	public UsuarioDTO trazerUsuario(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long usuarioId) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isAdmin()) {
			return usuarioService.trazer(usuarioId);
		}else {
			throw new AcessoNaoPermitidoException();
		}
	}

	@PostMapping("usuarios")
	public Usuario criarUsuario(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @Valid @RequestBody Usuario usuario) {
		UsuarioDTO usuariotoken = usuarioService.trazerPorToken(token);
		if(usuariotoken.isAdmin()) {
			return usuarioService.criar(usuario);
		}else {
			throw new AcessoNaoPermitidoException();
		}	
	}

	@PutMapping("usuarios/{usuarioId}")
	public Usuario alterarUsuario(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long usuarioId, @Valid @RequestBody Usuario usuarioRequest) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isAdmin()) {
			return usuarioService.alterar(usuarioId, usuarioRequest);
		}else {
			throw new AcessoNaoPermitidoException();
		}
	}

	@DeleteMapping("/usuarios/{usuarioId}")
	public void deletarUsuario(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long usuarioId) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isAdmin()) {
			usuarioService.deletar(usuarioId);
		}else {
			throw new AcessoNaoPermitidoException();
		}
	}
	
	/**
	 * Retorna uma lista com os usuario do tipo finalizador
	 * @param token
	 * @return
	 */
	@GetMapping("usuarios/finalizadores/")
	public List<UsuarioDTO> listarUsuariosPorTipo(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isAdmin() || usuario.isTriador()) {
			return usuarioService.listarFinalizadores();
		}else {
			throw new AcessoNaoPermitidoException();
		}
	}

}
