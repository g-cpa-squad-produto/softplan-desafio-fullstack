package com.miratanlehmkuhl.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miratanlehmkuhl.backend.dto.ListUser;
import com.miratanlehmkuhl.backend.dto.UserNewDTO;
import com.miratanlehmkuhl.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("")
	public ListUser users(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "offset", required = false, defaultValue = "10") Integer offset) {
		return service.findAll(PageRequest.of(page, offset));
	}

	@PostMapping("/new")
	public void save(@Valid @RequestBody UserNewDTO user) {
		// service.registration(user);
	}

}
