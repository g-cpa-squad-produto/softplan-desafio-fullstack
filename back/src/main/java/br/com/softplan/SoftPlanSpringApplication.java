package br.com.softplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.security.enums.PerfilEnum;
import br.com.softplan.usuario.modelos.Usuario;
import br.com.softplan.usuario.repository.UsuarioRepository;

@SpringBootApplication
@EnableJpaRepositories({ "br.com.softplan.usuario.repository", "br.com.softplan.processo.repository",
		"br.com.softplan.parecer.repository" })
@EntityScan({ "br.com.softplan.usuario.modelos", "br.com.softplan.processo.modelos",
		"br.com.softplan.parecer.modelos" })

@RestController
@EnableAsync
public class SoftPlanSpringApplication {

	@Autowired
	private UsuarioRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SoftPlanSpringApplication.class, args);
	}

	// Inserindo usuarios padroes
	@PostConstruct
	void started() {
		Usuario usuario1 = new Usuario();
		usuario1.setAtivo(Boolean.TRUE);
		usuario1.setNome("Usuario administrador");
		usuario1.setPerfil(PerfilEnum.ROLE_ADMINISTRADOR);
		usuario1.setLogin("loginadministrador");
		// 123456
		usuario1.setSenha("$2a$10$xo0Avm7fk/1IODbPzvFea.wUg5JhNmBd8ptRJzKjuzeoXIX0N6UGS");
		repository.save(usuario1);

		Usuario usuario2 = new Usuario();
		usuario2.setAtivo(Boolean.TRUE);
		usuario2.setNome("Usuario finalizador");
		usuario2.setPerfil(PerfilEnum.ROLE_USUARIO_FINALIZADOR);
		usuario2.setLogin("loginfinalizador");
		// 123456
		usuario2.setSenha("$2a$10$xo0Avm7fk/1IODbPzvFea.wUg5JhNmBd8ptRJzKjuzeoXIX0N6UGS");
		repository.save(usuario2);

		Usuario usuario3 = new Usuario();
		usuario3.setAtivo(Boolean.TRUE);
		usuario3.setNome("Usuario triador");
		usuario3.setPerfil(PerfilEnum.ROLE_USUARIO_TRIADOR);
		usuario3.setLogin("logintriador");
		// 123456
		usuario3.setSenha("$2a$10$xo0Avm7fk/1IODbPzvFea.wUg5JhNmBd8ptRJzKjuzeoXIX0N6UGS");
		repository.save(usuario3);

	}

	@GetMapping("/")
	public String get200() {
		return "Server working";
	}

}
