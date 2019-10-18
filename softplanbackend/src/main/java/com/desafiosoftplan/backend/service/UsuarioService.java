package com.desafiosoftplan.backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiosoftplan.backend.enums.UsuarioVisao;
import com.desafiosoftplan.backend.model.Usuario;
import com.desafiosoftplan.backend.repository.UsuarioRepository;
@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> list() {
		return usuarioRepository.findAll();
	}
	
	public Usuario create(Usuario entity) {
		return usuarioRepository.save(entity);
	}
	
	public Usuario update(Usuario entity) {
		return usuarioRepository.save(entity);
	}
	
	public void delete(String login) {
		usuarioRepository.deleteById(login);
	}
	
	public Usuario get(String login) {
		return usuarioRepository.getOne(login);
	}
	
}
