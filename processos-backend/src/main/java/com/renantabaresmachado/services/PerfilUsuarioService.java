package com.renantabaresmachado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.renantabaresmachado.domains.PerfilUsuario;
import com.renantabaresmachado.domains.Usuario;
import com.renantabaresmachado.repositories.PerfilUsuarioRepository;
import com.renantabaresmachado.services.exeptions.DataIntegrityException;
import com.renantabaresmachado.services.exeptions.ObjectNotFoundException;

@Service
public class PerfilUsuarioService {

	@Autowired
	private PerfilUsuarioRepository perfilUsuarioRepository;
	
	public PerfilUsuario inserir(PerfilUsuario perfilUsuario) {
		perfilUsuario.setId(null);
		perfilUsuario = perfilUsuarioRepository.save(perfilUsuario);
		return perfilUsuario;
	}
	
	public PerfilUsuario buscar(Integer id) {
		Optional<PerfilUsuario> perfilUsuario = perfilUsuarioRepository.findById(id);
		return perfilUsuario.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public PerfilUsuario editar(PerfilUsuario perfilUsuario) {
		buscar(perfilUsuario.getId());
		return perfilUsuarioRepository.save(perfilUsuario);
	}
	
	public void deletar(Integer id) {
		buscar(id);
		try {
			perfilUsuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar porque ha usuários relacionados.");
		}

	}
	
	public List<PerfilUsuario> buscarTodos() {
		return perfilUsuarioRepository.findAll();
}
}
