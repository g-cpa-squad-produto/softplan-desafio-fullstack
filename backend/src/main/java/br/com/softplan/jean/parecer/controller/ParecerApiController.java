package br.com.softplan.jean.parecer.controller;

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
import br.com.softplan.jean.parecer.entity.Parecer;
import br.com.softplan.jean.parecer.service.ParecerService;
import br.com.softplan.jean.usuario.entity.Usuario;
import br.com.softplan.jean.usuario.entity.UsuarioDTO;
import br.com.softplan.jean.usuario.service.UsuarioService;

@RestController
public class ParecerApiController {
	
	
	@Autowired
	private ParecerService parecerService;
	
	@Autowired
	private UsuarioService usuarioService;	

	@GetMapping("parecer")
	public List<Parecer> listar(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			return parecerService.listar();
		}
		return null;
	}
	
	@GetMapping("parecer/{parecerId}")
	public Parecer trazer(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long parecerId) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			return parecerService.trazer(parecerId);
		}
		return null;
	}
	
	@PostMapping("parecer")
	public Parecer criar(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @Valid @RequestBody Parecer parecer) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			return parecerService.criar(parecer);
		}
		return null;
	}


	@DeleteMapping("/parece/{parecerId}")
	public void deletar(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @PathVariable Long parecerId) {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			parecerService.deletar(parecerId);
		}
	}
	
	@PostMapping("parecer/processo")
	public Parecer criarParecerProcesso(@RequestHeader(LoginApiController.NOME_TOKEN_HEADER) String token, @Valid @RequestBody Parecer parecer) throws Exception {
		UsuarioDTO usuario = usuarioService.trazerPorToken(token);
		if(usuario.isFinalizador()) {
			Usuario usu = new Usuario();
			usu.setId(usuario.getId());
			parecer.setUsuario(usu);
			return parecerService.salvarParecerProcesso(parecer);
		}
		return null;
	}

}
