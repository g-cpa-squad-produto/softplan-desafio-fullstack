/**
 * 
 */
package br.com.softplan.security.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.response.Response;
import br.com.softplan.security.JwtUser;
import br.com.softplan.security.dto.JwtAuthenticationDto;
import br.com.softplan.security.dto.TokenDto;
import br.com.softplan.security.utils.JwtTokenUtil;

/**
 * @author emanuel
 *
 */
@RestController
@RequestMapping("/api-publica")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Gera e retorna um novo token JWT.
	 */
	@PostMapping("/login")
	public ResponseEntity<Response<TokenDto>> gerarTokenJwt(@Valid @RequestBody JwtAuthenticationDto authenticationDto,
			BindingResult result) {

		Response<TokenDto> response = new Response<TokenDto>();

		if (result.hasErrors()) {
			log.error("Erro validando lancamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		log.info("Gerando um token para o login {}", authenticationDto.getLogin());

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationDto.getLogin(), authenticationDto.getSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(authenticationDto.getLogin());

		String token = jwtTokenUtil.obterToken(userDetails);
		response.setData(new TokenDto(token));

		return ResponseEntity.ok(response);

	}
}