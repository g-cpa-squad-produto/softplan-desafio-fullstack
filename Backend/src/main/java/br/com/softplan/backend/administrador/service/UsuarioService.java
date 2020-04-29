package br.com.softplan.backend.administrador.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.softplan.backend.administrador.model.UsuarioModel;
import br.com.softplan.backend.administrador.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public UsuarioModel saveUsuario(UsuarioModel usuarioModel){
		return usuarioRepository.save(usuarioModel);
	}

	public List<UsuarioModel> findAll() {
		return usuarioRepository.findAll();
	}
}
