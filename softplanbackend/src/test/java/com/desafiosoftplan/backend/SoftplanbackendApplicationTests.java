package com.desafiosoftplan.backend;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafiosoftplan.backend.model.Situacao;
import com.desafiosoftplan.backend.model.Usuario;
import com.desafiosoftplan.backend.service.SituacaoService;
import com.desafiosoftplan.backend.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftplanbackendApplicationTests {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Test
	public void createUsuarios() {
		System.out.println("TESTE");
		List<Usuario> users =new ArrayList<>();
		users = usuarioService.list();
		if(users.isEmpty()) {
			usuarioService.create(new Usuario("admin", "USUARIO ADMINISTRADOR", new BCryptPasswordEncoder().encode("admin@123")));
			usuarioService.create(new Usuario("triador", "USUARIO TRIADOR", new BCryptPasswordEncoder().encode("tria@123")));
			usuarioService.create(new Usuario("finalizador", "USUARIO FINALIZADOR", new BCryptPasswordEncoder().encode("fina@123")));
		}
	}

}
