package br.com.softplan.jean.login.controller;

import java.security.Key;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.jean.usuario.entity.Usuario;
import br.com.softplan.jean.usuario.entity.UsuarioDTO;
import br.com.softplan.jean.usuario.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
	
@RestController
public class LoginApiController {
	
	public static final String NOME_TOKEN_HEADER = "token";	
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("login")
	public ResponseEntity<String> login(@Valid @RequestParam("login") String login, @RequestParam("senha") String senha, HttpServletResponse response ) {
		Usuario usuarioLogin = usuarioService.trazerPorLogin(login);
		if(usuarioLogin!=null && usuarioLogin.getSenha().equals(senha) ) {
			Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
			String jws = Jwts.builder().setSubject(usuarioLogin.getNome())
					.setSubject(usuarioLogin.getLogin())
					.setSubject(usuarioLogin.getTipoUsuario().toString())
					.signWith(key).compact();
			response.setHeader("Authorization", jws);
			usuarioLogin.setToken(jws);
			usuarioService.alterarToken(usuarioLogin.getId(), usuarioLogin);
			return new ResponseEntity<String>("sucesso", HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
	}
	
	@GetMapping("login/refresh")
	public ResponseEntity<String> loginRefresh(@RequestHeader(NOME_TOKEN_HEADER) String token) {
		if(token != null) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
	}
	
	
	@GetMapping("login/usuario")
	public UsuarioDTO trazerUsuarioToken(@RequestHeader(NOME_TOKEN_HEADER) String token) {
		return usuarioService.trazerPorToken(token);
	}

}
