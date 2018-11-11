package com.miratanlehmkuhl.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miratanlehmkuhl.backend.dto.UserNewDTO;
import com.miratanlehmkuhl.backend.model.User;
import com.miratanlehmkuhl.backend.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/new")
	public void save(@Valid @RequestBody UserNewDTO user) {
		service.registration(user);
	}

	@GetMapping("/users")
	public String helloUser() throws JsonProcessingException {
		List<User> users = service.listAll();
		ObjectMapper mapper = new ObjectMapper();
		String arrayToJson = mapper.writeValueAsString(users);
		return arrayToJson;
	}

}
