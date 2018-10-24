package br.com.softplan.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.models.User;

@RestController
@RequestMapping("/users")
public class UserController extends GenericController<User, Long> {


}
