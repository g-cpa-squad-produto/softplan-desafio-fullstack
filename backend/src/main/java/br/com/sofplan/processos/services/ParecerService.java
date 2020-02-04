package br.com.sofplan.processos.services;

import br.com.sofplan.processos.dto.v1.ParecerDTO;
import br.com.sofplan.processos.dto.v1.UsuarioDTO;

public interface ParecerService {

	ParecerDTO findById(Long id);
	
	ParecerDTO create(ParecerDTO request, UsuarioDTO usuarioJwt);
	
	ParecerDTO update(Long id, ParecerDTO request);
	
	void delete(Long id);
	
}
