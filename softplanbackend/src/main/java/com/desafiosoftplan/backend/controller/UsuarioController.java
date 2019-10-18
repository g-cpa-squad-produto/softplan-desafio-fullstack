package com.desafiosoftplan.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafiosoftplan.backend.enums.UsuarioVisao;
import com.desafiosoftplan.backend.model.Usuario;
import com.desafiosoftplan.backend.model.dto.UsuarioDTO;
import com.desafiosoftplan.backend.model.dto.UsuarioRespostaDTO;
import com.desafiosoftplan.backend.service.UsuarioService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping("/usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsuarioController{

	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET, produces= {MediaType.APPLICATION_JSON})
	public ResponseEntity<List<Usuario>> list() {
		return new ResponseEntity<List<Usuario>>(usuarioService.list(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "{login}", method = RequestMethod.PUT,consumes= {MediaType.APPLICATION_JSON})
	public ResponseEntity<Usuario> update(@PathVariable(value = "login") String login, @RequestBody @Valid Usuario entity) {
		return new ResponseEntity<Usuario>(usuarioService.update(entity),HttpStatus.OK);
	}
	
	@RequestMapping(value = "{login}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "login") String login) {
		usuarioService.delete(login);
	}
	
	@RequestMapping(value = "{login}", method = RequestMethod.GET, produces= {MediaType.APPLICATION_JSON})
	public ResponseEntity<Usuario> get(@PathVariable(value = "login") String login) {
		return new ResponseEntity<Usuario>(usuarioService.get(login),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,consumes= {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<UsuarioRespostaDTO> create(@RequestBody @Valid UsuarioDTO entity) {
		Usuario usuario = entity.toObj();
		usuario = usuarioService.create(usuario);
		return new ResponseEntity<>(UsuarioRespostaDTO.toDTO(usuario),HttpStatus.OK);
	}
	
}
