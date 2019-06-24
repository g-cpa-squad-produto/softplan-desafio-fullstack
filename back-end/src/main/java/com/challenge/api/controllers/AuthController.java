package com.challenge.api.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.api.configs.JwtTokenProvider;
import com.challenge.api.documents.Role;
import com.challenge.api.documents.User;
import com.challenge.api.repositories.UserRepository;
import com.challenge.api.responses.Response;
import com.challenge.api.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository users;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody User user,BindingResult result) {
		try {
			String username = user.getUsername();
			Set<Role> roles = this.users.findByUsername(username).getRoles();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
			String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("roles", roles);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			List<String> errors = new ArrayList<String>();
			errors.add("Invalid username/password");
			
			return ResponseEntity.badRequest().body(new Response<User>(errors));
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/register")
	public ResponseEntity register(@Valid @RequestBody User user,BindingResult result) {
		User userExists = userServiceImpl.findUserByUsername(user.getUsername());
		
		if (userExists != null) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(error -> errors.add("User with username: " + user.getUsername() 
			+ " already exists"));
			
			return ResponseEntity.badRequest().body(new Response<User>(errors));
		}else if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<User>(errors));
		}
		
		return ResponseEntity.ok(new Response<User>(this.userServiceImpl.create(user)));	
	}
}

