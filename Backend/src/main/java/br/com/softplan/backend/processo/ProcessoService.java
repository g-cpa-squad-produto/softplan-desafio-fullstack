package br.com.softplan.backend.processo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
