package com.miratanlehmkuhl.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.miratanlehmkuhl.backend.enums.Role;
import com.miratanlehmkuhl.backend.model.User;
import com.miratanlehmkuhl.backend.service.UserService;

@Component
public class DemoData implements CommandLineRunner {

	@Autowired
	private UserService service;

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setName("Screening");
		user.setEmail("screening@gmail.com");
		user.setPassword("1111");
		user.grantRole(Role.SCREENING);
		service.save(user);

		user = new User();
		user.setName("Finisher");
		user.setEmail("finisher@gmail.com");
		user.setPassword("2222");
		user.grantRole(Role.FINISHER);
		service.save(user);

		user = new User();
		user.setName("Admin");
		user.setEmail("admin@gmail.com");
		user.setPassword("3333");
		user.grantRole(Role.ADMIN);
		service.save(user);

		for (int i = 0; i < 20; i++) {
			user = new User();
			user.setName("Usuário " + i);
			user.setEmail("admin" + i + "@gmail.com");
			user.setPassword("999" + i);
			user.grantRole(Role.ADMIN);
			service.save(user);
		}
	}

}
