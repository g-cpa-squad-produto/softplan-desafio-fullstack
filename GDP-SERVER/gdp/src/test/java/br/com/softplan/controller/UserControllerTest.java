package br.com.softplan.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.softplan.services.UserService;

public class UserControllerTest  {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService service;
	

	@Test
	public void up() {
		
	}
}