package br.com.evaluation.process.manager.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.evaluation.process.manager.domain.RoleType;
import br.com.evaluation.process.manager.entity.User;
import br.com.evaluation.process.manager.repository.UserRepository;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories("br.com.evaluation.process.manager.repository")
@ComponentScan("br.com.evaluation.process.manager")
@EntityScan("br.com.evaluation.process.manager.entity") 
public class App {
	
	public App() {
		
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = 
				SpringApplication.run(App.class, args);
		
		UserRepository repository = applicationContext.getBean(UserRepository.class);
        User u = new User();
        u.setName("Administrador");
        u.setStatus(true);
        u.setRole(RoleType.ADMIN);
        u.setLogin("admin");
        u.setPassword("admin");
		repository.save(u);
		User u2 = new User();
		u2.setName("User manager");
		u2.setStatus(true);
		u2.setRole(RoleType.USER_MANAGER);
		u2.setLogin("user1");
		u2.setPassword("user1");
		repository.save(u2);
	}
}
