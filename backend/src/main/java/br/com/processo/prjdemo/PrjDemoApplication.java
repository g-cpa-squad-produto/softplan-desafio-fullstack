package br.com.processo.prjdemo;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import br.com.processo.prjdemo.model.EnumPermissaoUsuario;
import br.com.processo.prjdemo.model.Usuario;
import br.com.processo.prjdemo.repository.UsuarioRepository;

/**
 * 
 * @author Guilherme Sena
 * 
 */
@SpringBootApplication
@EnableAutoConfiguration
public class PrjDemoApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(PrjDemoApplication.class);

	public static void main(String[] args) {
		//Inicializa o spring boot
		SpringApplication.run(PrjDemoApplication.class, args);
	}
	
	/**
	 * 
	 * @param usuarioRepository
	 * @return Insere dados no banco de dados
	 */
	@Bean
	public CommandLineRunner setup(UsuarioRepository usuarioRepository) {
		return (args) -> {
			//Insere alguns usuário no sistema
			usuarioRepository.save(new Usuario("admin", "admin", "123123", new Date(), new HashSet<EnumPermissaoUsuario>(Arrays.asList(EnumPermissaoUsuario.ADMINISTRADOR))));
			usuarioRepository.save(new Usuario("triador", "triador", "123123", new Date(), new HashSet<EnumPermissaoUsuario>(Arrays.asList(EnumPermissaoUsuario.TRIADOR))));
			usuarioRepository.save(new Usuario("finalizador", "finalizador", "123", new Date(), new HashSet<EnumPermissaoUsuario>(Arrays.asList(EnumPermissaoUsuario.FINALIZADOR))));
			
			logger.info("Inserts iniciais no banco realizados!!!");
		};
	}
}
