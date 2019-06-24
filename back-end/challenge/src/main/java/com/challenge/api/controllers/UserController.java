package com.challenge.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.api.documents.User;
import com.challenge.api.responses.Response;
import com.challenge.api.services.UserService;
import com.challenge.api.services.impl.UserServiceImpl;

@RestController
@RequestMapping(path="/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
		
	@GetMapping
	public ResponseEntity<Response<List<User>>> listAll(){
		return ResponseEntity.ok(new Response<List<User>>(this.userService.listAll()));
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Response<User>> listById(@PathVariable(name = "id") String id){
		return ResponseEntity.ok(new Response<User>(this.userService.listByID(id)));
	}
	
	@PostMapping
	public ResponseEntity<Response<User>> create(@Valid @RequestBody User user, BindingResult result){
		User userExists = userServiceImpl.findUserByUsername(user.getUsername());
		
		if (userExists != null) {
			List<String> errors = new ArrayList<String>();
			errors.add("User already exists");
			
			return ResponseEntity.badRequest().body(new Response<User>(errors));
		}else if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<User>(errors));
		}
		
		return ResponseEntity.ok(new Response<User>(this.userServiceImpl.create(user)));		
	}
	
	@PostMapping(path="/{id}")
	public ResponseEntity<Response<User>> update(@PathVariable(name="id") String id,@Valid @RequestBody User user, BindingResult result){
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<User>(errors));
		}
		
		user.setId(id);
		return ResponseEntity.ok(new Response<User>(this.userServiceImpl.update(user)));		
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Response<Integer>> remove(@PathVariable(name="id") String id){
		this.userService.remove(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}
}
