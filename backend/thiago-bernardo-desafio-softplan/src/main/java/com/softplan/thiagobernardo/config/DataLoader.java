package com.softplan.thiagobernardo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.softplan.thiagobernardo.usuario.entity.Usuario;
import com.softplan.thiagobernardo.usuario.repository.UsuarioRepository;
import com.softplan.thiagobernardo.util.TipoUsuario;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

    public void run(ApplicationArguments args) {
    	Usuario usuario = usuarioRepository.findByLogin("admin");
    	if(usuario == null) {
    		usuarioRepository.save(new Usuario("Admin","admin","admin",TipoUsuario.ADMIN));
    	}
    }

}
