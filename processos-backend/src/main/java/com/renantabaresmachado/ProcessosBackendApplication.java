package com.renantabaresmachado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.renantabaresmachado.domains.Usuario;
import com.renantabaresmachado.enuns.Perfil;
import com.renantabaresmachado.services.UsuarioService;

@SpringBootApplication
public class ProcessosBackendApplication implements CommandLineRunner {
	
	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(ProcessosBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario(null, "usuarioADM", "adm@softplan.com.br", "12345");
		Usuario usuario1 = new Usuario(null, "usuarioTRIADOR", "triado@softplan.com.br", "12345");
		Usuario usuario2 = new Usuario(null, "usuarioFINALIZADOR", "finalizador@softplan.com.br", "12345");
		usuario.addPerfil(Perfil.ADMIN);
		usuario1.addPerfil(Perfil.TRIADOR);
		usuario2.addPerfil(Perfil.FINALIZADOR);
		usuarioService.inserir(usuario);
		usuarioService.inserir(usuario1);
		usuarioService.inserir(usuario2);
		
	}	
}
