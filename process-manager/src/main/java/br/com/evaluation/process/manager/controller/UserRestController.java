package br.com.evaluation.process.manager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.evaluation.process.manager.domain.RoleType;
import br.com.evaluation.process.manager.entity.User;
import br.com.evaluation.process.manager.service.UserService;
import br.com.evaluation.process.manager.validator.UserValidator;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/save")
	public User save(@RequestBody @Valid  User user) {
		return userService.save(user);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findAll")
	public Iterable<User> findAll() {
		return userService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/findByRole/{role}")
	public Iterable<User> findByRole(@PathVariable("role") RoleType role) {
		return userService.findByRole(role);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/authenticate")
	public User findByRole(@RequestBody User user) {
		return userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public void remove(@PathVariable(value = "id") Long id) {
		userService.delete(id);
	}

}
