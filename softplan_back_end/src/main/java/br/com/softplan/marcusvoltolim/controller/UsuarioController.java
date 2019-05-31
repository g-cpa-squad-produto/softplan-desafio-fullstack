package br.com.softplan.marcusvoltolim.controller;

import br.com.softplan.marcusvoltolim.model.Usuario;
import br.com.softplan.marcusvoltolim.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController extends AbstractController<Usuario> {
	
	@SuppressWarnings({"FieldCanBeLocal", "unused"})
	private UsuarioService usuarioService;
	
	@Autowired
	UsuarioController(UsuarioService usuarioService) {
		super(usuarioService);
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/findAllNomeUsuarioFinalizadores")
	public List<String> findAllNomeUsuarioFinalizadores() {
		return usuarioService.findAllNomeUsuarioFinalizadores();
	}
	
	
}
