package com.renantabaresmachado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.renantabaresmachado.domains.Usuario;
import com.renantabaresmachado.repositories.UsuarioRepository;
import com.renantabaresmachado.services.exeptions.DataIntegrityException;
import com.renantabaresmachado.services.exeptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscar(Integer id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return usuario.get();
		} else {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}

	}

	public Usuario inserir(Usuario usuario) {
		usuario.setId(null);
		usuario = usuarioRepository.save(usuario);
		return usuario;
	}

	public Usuario editar(Usuario usuario) {
		buscar(usuario.getId());
		return usuarioRepository.save(usuario);
	}

	public void deletar(Integer id) {
		buscar(id);
		try {
			usuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar porque ha pareceres relacionados.");
		}

	}

	public List<Usuario> buscarTodos() {
			return usuarioRepository.findAll();
	}
}
