package fmreina.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fmreina.app.model.User;
import fmreina.app.persistence.UserRepository;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/users/{username}/users")
	public List<User> getAllUsers(@PathVariable String username) {
		return userRepository.findAll();
	}

	@DeleteMapping("/users/{username}/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String username, @PathVariable Long id) {
		userRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/users/{username}/users/{id}")
	public User getUser(@PathVariable String username, @PathVariable Long id){
		Optional<User> op = userRepository.findById(id);
		
		if(op.isPresent()){
			return op.get();
		}
		
		return null;
	}

	@PutMapping("/users/{username}/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String username, @PathVariable Long id,
			@RequestBody User user) {
		User userUpdated = userRepository.save(user);

		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
	}

	@PostMapping("/users/{username}/users/{id}")
	public ResponseEntity<User> createUser(@PathVariable String username, @RequestBody User user) {
		User userCreated = userRepository.save(user);

		// Pega a url do recurso
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
}
