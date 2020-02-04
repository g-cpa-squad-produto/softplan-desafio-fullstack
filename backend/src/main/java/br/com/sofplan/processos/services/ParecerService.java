package br.com.sofplan.processos.services;

import br.com.sofplan.processos.dto.v1.ParecerDTO;

public interface ParecerService {

	ParecerDTO findById(Long id);
	
	ParecerDTO create(ParecerDTO request);
	
	ParecerDTO update(Long id, ParecerDTO request);
	
	void delete(Long id);
	
}
