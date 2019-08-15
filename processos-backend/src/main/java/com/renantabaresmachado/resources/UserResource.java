package com.renantabaresmachado.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renantabaresmachado.security.UserSSecurity;
import com.renantabaresmachado.services.UserService;
import com.renantabaresmachado.services.exeptions.AuthorizationException;

@RestController
@RequestMapping(value = "/userresource")
public class UserResource {
	
	@GetMapping
	public UserSSecurity buscarPerfil() {	
		UserSSecurity user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		return user;
	}

}
