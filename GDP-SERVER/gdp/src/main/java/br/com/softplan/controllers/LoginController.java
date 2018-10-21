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
import br.com.softplan.models.User;
import br.com.softplan.services.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

    @RequestMapping(value="/autenticate", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<User> autenticate(@RequestBody @Valid User user) {
    	
    	User userResponse  = this.userService.autenticate(user);
    	
    	if (userResponse == null) {
            throw new UserNotFoundException("User not found");
    	}
    	
		return new ResponseEntity<>(this.userService.autenticate(user), HttpStatus.OK);
	}

}
