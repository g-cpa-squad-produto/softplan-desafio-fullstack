package br.com.softplan.jean.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.softplan.jean.usuario.entity.Usuario;
import br.com.softplan.jean.usuario.repository.UsuarioRepository;
import br.com.softplan.jean.util.TipoUsuario;


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