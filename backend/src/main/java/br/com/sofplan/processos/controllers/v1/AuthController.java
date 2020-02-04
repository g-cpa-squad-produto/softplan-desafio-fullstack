package br.com.sofplan.processos.controllers.v1;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sofplan.processos.dto.v1.LoginDTO;
import br.com.sofplan.processos.dto.v1.LoginResponseDTO;
import br.com.sofplan.processos.services.AuthService;
import io.swagger.annotations.Api;

@Api(tags = { "Auth" })
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/login")
	public LoginResponseDTO login(@RequestBody @Valid LoginDTO request) {
		return authService.login(request);
	}

}
