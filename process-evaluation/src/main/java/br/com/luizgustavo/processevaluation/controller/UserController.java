package br.com.luizgustavo.processevaluation.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luizgustavo.processevaluation.model.dto.UserDto;
import br.com.luizgustavo.processevaluation.model.form.UserForm;
import br.com.luizgustavo.processevaluation.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserDto> insert(@Valid @RequestBody UserForm form, HttpServletResponse response) {
		UserDto user = this.userService.insert(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getIdUser()).toUri();
		response.addHeader("Location", uri.toASCIIString());
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserDto> update(@PathVariable("id") Long idUser, @Valid @RequestBody UserForm form) {
		UserDto user = this.userService.update(idUser, form);
		return ResponseEntity.ok(user);		
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<UserDto>> findAll(Pageable pageable) {
		Page<UserDto> users = this.userService.findAll(pageable);
		return ResponseEntity.ok(users);
	}
		
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserDto> findById(@PathVariable("id") Long idUser) {
		UserDto user = this.userService.findById(idUser);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long idUser) {
		this.userService.delete(idUser);
	}
	
	@GetMapping("/closers")
	@PreAuthorize("hasRole('ROLE_SCREENING')")
	public ResponseEntity<List<UserDto>> findClosers() {
		List<UserDto> closers = this.userService.findClosers();
		return ResponseEntity.ok(closers);
	}
	
}
