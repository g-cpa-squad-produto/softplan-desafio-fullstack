package br.com.softplan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.models.User;
import br.com.softplan.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping()
	public ResponseEntity<List<User>> all() {
		return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		this.userService.delete(id);
	}

}
