package br.com.sofplan.processos.services;

import java.util.List;

import br.com.sofplan.processos.dto.v1.CreateUsuarioDTO;
import br.com.sofplan.processos.dto.v1.UsuarioDTO;

public interface UsuarioService {

	List<UsuarioDTO> find();
	
	UsuarioDTO findById(Long id);
	
	UsuarioDTO findByEmail(String email);
	
	UsuarioDTO create(CreateUsuarioDTO request);
	
	UsuarioDTO update(Long id, UsuarioDTO request);
	
	void delete(long id);
	
}
