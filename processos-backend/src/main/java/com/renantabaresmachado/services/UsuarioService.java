package com.renantabaresmachado.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renantabaresmachado.domains.Usuario;
import com.renantabaresmachado.repositories.UsuarioRepository;
import com.renantabaresmachado.services.exeptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario buscar(Integer id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			return usuario.get();
		} else {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+", Tipo: " + Usuario.class.getName());
		}

	}
}
