package br.com.softplan.security.controller;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.entity.Usuario;
import br.com.softplan.security.jwt.JwtAuthenticationRequest;
import br.com.softplan.security.jwt.JwtTokenUtil;
import br.com.softplan.security.model.CurrentUser;
import br.com.softplan.service.UsuarioService;

@RestController
@CrossOrigin(origins="*")
public class AutenticacaoRestController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(value="/api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
		final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequest.getEmail(), 
							authenticationRequest.getSenha())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generatedToken(userDetails);
		final Usuario usuario = usuarioService.findByEmail(authenticationRequest.getEmail());
		usuario.setSenha(null);
		System.out.println(token + " generated");
		return ResponseEntity.ok(new CurrentUser(token, usuario));
	}
	
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		final Usuario usuario = usuarioService.findByEmail(username);
		
		if(jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUser(refreshedToken, usuario));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
