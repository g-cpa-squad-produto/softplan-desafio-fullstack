package br.com.softplan.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.exceptions.UserNotFoundException;
import br.com.softplan.exceptions.UserUnauthorizedException;
import br.com.softplan.models.Token;
import br.com.softplan.models.User;
import br.com.softplan.services.TokenService;
import br.com.softplan.services.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;

    @RequestMapping(value="/autenticate", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<Token> autenticate(@RequestBody @Valid User user) {
    	
    	User userResponse  = this.userService.autenticate(user);
    	
    	if (userResponse == null) {
            throw new UserUnauthorizedException("Unauthorized");
    	}
    	
		return new ResponseEntity<>(this.tokenService.getToken(), HttpStatus.OK);
	}
    
    @RequestMapping(value="/user", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<User> getUserByLogin(@RequestBody User user) {
    	
    	User userResponse  = this.userService.getUserByLogin(user.getLogin());
    	
    	if (userResponse == null) {
            throw new UserNotFoundException("User not found");
    	}
    	
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

}
