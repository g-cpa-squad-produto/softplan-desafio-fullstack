package br.com.softplan.process;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.softplan.process.api.entity.User;
import br.com.softplan.process.api.enums.ProfileEnum;
import br.com.softplan.process.api.repository.UserRepository;

@SpringBootApplication
public class ProcessApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessApiApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userRepository, passwordEncoder);
		};
	}
	
	private void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		User admin = new User();
		admin.setEmail("admin@email.com.br");
		admin.setPassword(passwordEncoder.encode("12345"));
		admin.setProfile(ProfileEnum.ROLE_ADMIN);
		
		User find = userRepository.findByEmail("admin@email.com.br");
		if (find == null) {
			userRepository.save(admin);
		}
	}
	
}
