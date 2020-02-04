package br.com.sofplan.processos.services;

import java.util.List;

import br.com.sofplan.processos.dto.v1.ProcessoDTO;
import br.com.sofplan.processos.dto.v1.UsuarioDTO;

public interface ProcessoService {

	List<ProcessoDTO> find();
	
	ProcessoDTO findById(Long id);
	
	ProcessoDTO create(ProcessoDTO request, UsuarioDTO usuarioJwt);
	
	ProcessoDTO update(Long id, ProcessoDTO request);
	
	void delete(Long id);
	
	void addResponsavel(Long processoId, Long responsavelId, UsuarioDTO usuarioJwt);
	
	void deleteResponsavel(Long processoId, Long responsavelId);
	
}
