package br.com.ramonbarros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ramonbarros.entity.Parecer;
import br.com.ramonbarros.entity.Processo;
import br.com.ramonbarros.entity.Usuario;
import br.com.ramonbarros.enuns.PerfilEnum;
import br.com.ramonbarros.enuns.StatusProcessoEnum;
import br.com.ramonbarros.service.ProcessoService;
import br.com.ramonbarros.service.UsuarioService;

@SpringBootApplication
public class GestaoProcessosBackendApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProcessoService processoService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestaoProcessosBackendApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario(null, "Administrador", "admin", "123", "123", PerfilEnum.ADMIN);
		usuarioService.salvar(usuario);
		
		Processo processo = new Processo(null, StatusProcessoEnum.PENDENTE);
		Parecer parecer = new Parecer(null, 1L, 1L, "Processo iniciado com pendencias.");
		processo.getListaParecer().add(parecer);
		processoService.salvar(processo);
	}

}
