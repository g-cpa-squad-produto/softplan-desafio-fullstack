package com.isadora.backendapi;

import com.isadora.backendapi.domain.Usuario;
import com.isadora.backendapi.enums.UserEnum;
import com.isadora.backendapi.enums.UserStatus;
import com.isadora.backendapi.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApiApplication.class, args);
	}


//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

	// Criando usuário ao iniciar a aplicação
	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository,  PasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(usuarioRepository, passwordEncoder);
		};
	}

	private void initUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		/*Salvando um usuário admin*/
		Usuario user = usuarioRepository.findByEmail("admin@softplan.com.br");
		if (user == null) {
			Usuario admin = new Usuario();
			admin.setEmail("admin@softplan.com.br");
			admin.setpassword(passwordEncoder.encode("123456"));
			admin.setStatus(UserStatus.ATIVO);
			admin.setTipo(UserEnum.ROLE_ADMIN);
			usuarioRepository.save(admin);
		}

		/*Salvando um usuário triador*/
		user = usuarioRepository.findByEmail("triador@softplan.com.br");
		if (user == null) {
			Usuario triador = new Usuario();
			triador.setEmail("triador@softplan.com.br");
			triador.setpassword(passwordEncoder.encode("123456"));
			triador.setStatus(UserStatus.ATIVO);
			triador.setTipo(UserEnum.ROLE_TRIADOR);
			usuarioRepository.save(triador);
		}

		/*Salvando um usuário finalizador*/
		user = usuarioRepository.findByEmail("finalizador@softplan.com.br");
		if (user == null) {
			Usuario finalizador = new Usuario();
			finalizador.setEmail("finalizador@softplan.com.br");
			finalizador.setpassword(passwordEncoder.encode("123456"));
			finalizador.setStatus(UserStatus.ATIVO);
			finalizador.setTipo(UserEnum.ROLE_FINALIZADOR);
			usuarioRepository.save(finalizador);
		}
	}

}
