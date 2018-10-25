package br.com.softplan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.entity.ResponseEntity;
import br.com.softplan.entity.Usuario;
import br.com.softplan.service.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> listAll(){
		return usuarioService.listAll();
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity salvar(@RequestBody Usuario usuario)
	{
		return usuarioService.salvar(usuario);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity atualizar(@RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}
	
	@DeleteMapping(value="excluir/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity deletar(@PathVariable("id") String id) {
		return usuarioService.delete(id);
	}
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public @ResponseBody Usuario getById(@PathVariable("id") String id) {
		return usuarioService.findById(id);
	}

}
