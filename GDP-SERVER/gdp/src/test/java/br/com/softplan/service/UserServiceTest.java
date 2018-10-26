package br.com.softplan.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.softplan.ApplicationTest;
import br.com.softplan.services.UserService;

public class UserServiceTest extends ApplicationTest {

	@Autowired
	private UserService userService;
	
	@Before
	public void setup() {
		this.userService.deleteAll();
	}

	@Test
	public void deveBuscarUseres() {
		assertEquals(new ArrayList<>(), this.userService.findAll());
	}

}