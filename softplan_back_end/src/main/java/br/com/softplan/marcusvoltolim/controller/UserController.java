package br.com.softplan.marcusvoltolim.controller;

import br.com.softplan.marcusvoltolim.model.UsuarioLogin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class UserController {
	
	@RequestMapping("/login")
	public boolean login(@RequestBody UsuarioLogin user) {
		return user.getUsername().equals("user") && user.getPassword().equals("password");
	}
	
	@RequestMapping("/authorization")
	public Principal user(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
		return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
	}
	
}
