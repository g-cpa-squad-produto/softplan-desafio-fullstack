package br.com.softplan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.softplan.entity.Usuario;
import br.com.softplan.enums.PerfilEnum;
import br.com.softplan.repository.UsuarioRepository;

@SpringBootApplication
public class BackendSoftplanApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendSoftplanApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UsuarioRepository repository, PasswordEncoder encoder) {
		return args -> {
			initUser(repository, encoder);
		};
	}
	
	private void initUser(UsuarioRepository repository, PasswordEncoder encoder) {
		Usuario usuario = new Usuario();
		usuario.setNome("Administrador");
		usuario.setEmail("administrador@softplan.com.br");
		usuario.setSenha(encoder.encode("123456"));
		usuario.setPerfil(PerfilEnum.ROLE_ADMIN);
		
		Usuario finded = repository.findByEmail("administrador@softplan.com.br");
		if(finded == null) {
			repository.save(usuario);
		}
	}
}
