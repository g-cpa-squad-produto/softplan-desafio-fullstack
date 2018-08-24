package br.com.danilopaixao.ws.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.danilopaixao.ws.user.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
public class UserRestController {

	@Autowired
	private UserService service;

	@ApiOperation("EndPoint to get User by ID ")
	@GetMapping(value = "/api/v1/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String getUser(
    		@PathVariable(value = "id", required = true) final String id ) {

		return this.service.getById(id);
	}	
    		
	
	
	@ApiOperation("Endpoint to create new User")
	@PostMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String saveUser(
    		@RequestBody(required = true) final String user) {
		return this.service.save(user);
    }

}

