package br.com.softplan.backend.triador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.softplan.backend.triador.model.ProcessoModel;
import br.com.softplan.backend.triador.repository.ProcessoRepository;

@Service
public class ProcessoService {

	private final ProcessoRepository processoRepository;

	public ProcessoService(ProcessoRepository processoRepository) {
		this.processoRepository = processoRepository;
	}

	public ProcessoModel saveProcesso(ProcessoModel processoModel){
		return processoRepository.save(processoModel);
	}

	public List<ProcessoModel> findAll() {
		return processoRepository.findAll();
	}

	public Optional<ProcessoModel> findById(String processoId) {
		return processoRepository.findById(processoId);
	}
}
