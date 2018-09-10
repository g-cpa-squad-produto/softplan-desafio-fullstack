package br.com.softplan.process.api.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.process.api.entity.User;
import br.com.softplan.process.api.enums.ProfileEnum;
import br.com.softplan.process.api.response.Response;
import br.com.softplan.process.api.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<User>> create(HttpServletRequest request, @RequestBody User user,
			BindingResult result) {
		
		Response<User> response = new Response<User>();
		
		try {
			validateCreateUser(user, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userPersisted = (User) userService.createOrUpdate(user);
			response.setData(userPersisted);
			
		} catch (DuplicateKeyException dE) {
			response.getErrors().add("Email already registered!");
			return ResponseEntity.badRequest().body(response);
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	private void validateCreateUser(User user, BindingResult result) {
		if (user.getEmail() == null) {
			result.addError(new ObjectError("User", "Email não informado"));
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> update(HttpServletRequest request, @RequestBody User user,
			BindingResult result) {
		Response<User> response = new Response<User>();
		
		try {
			validateUpdateUser(user, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userPersisted = (User) userService.createOrUpdate(user);
			response.setData(userPersisted);
					
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}		
		
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdateUser(User user, BindingResult result) {
		if(user.getId() == null) {
			result.addError(new ObjectError("User", "Id não informado"));
		}
		
		if(user.getEmail() == null) {
			result.addError(new ObjectError("User", "Email não informado"));
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> findById(@PathVariable("id") String id) {
		Response<User> response = new Response<User>();
		User user = userService.findById(id);

		if (user == null) {
			response.getErrors().add("Registro não encontrado id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(user);		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		User user = userService.findById(id);

		if (user == null) {
			response.getErrors().add("Registro não encontrado id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		userService.delete(id);
		
		return ResponseEntity.ok(new Response<String>());
	}	
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<User>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<User>> response = new Response<Page<User>>();
		Page<User> users = userService.findAll(page, count);
		response.setData(users);
		return ResponseEntity.ok(response);		
	}
	
	@GetMapping(value = "reviews")
	@PreAuthorize("hasAnyRole('ROLE_PROCESS_STARTER')")
	public ResponseEntity<Response<Iterable<User>>> findAllReviews() {
		Response<Iterable<User>> response = new Response<Iterable<User>>();		
		Iterable<User> users = userService.findAllByProfile(ProfileEnum.ROLE_PROCESS_END);		
		response.setData(users);
		return ResponseEntity.ok(response);		
	}

}
