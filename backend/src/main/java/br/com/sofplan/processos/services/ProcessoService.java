package br.com.sofplan.processos.services;

import java.util.List;

import br.com.sofplan.processos.dto.v1.ProcessoDTO;

public interface ProcessoService {

	List<ProcessoDTO> find();
	
	ProcessoDTO findById(Long id);
	
	ProcessoDTO create(ProcessoDTO request);
	
	ProcessoDTO update(Long id, ProcessoDTO request);
	
	ProcessoDTO delete(Long id);
	
}
