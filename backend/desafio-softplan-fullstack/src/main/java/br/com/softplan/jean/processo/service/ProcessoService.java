package br.com.softplan.jean.processo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.jean.processo.entity.Processo;
import br.com.softplan.jean.processo.entity.ProcessoDTO;
import br.com.softplan.jean.processo.repository.ProcessoRepository;
import br.com.softplan.jean.util.ParecerStatus;

@Service
public class ProcessoService {
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	public List<ProcessoDTO> listar() {
		return ProcessoDTO.toListDTO(processoRepository.findAll());
	}
	
	public ProcessoDTO trazer(Long processoId) {
		Processo processo = processoRepository.findById(processoId).orElseThrow(() -> new RuntimeException("Processo não encontrado!"));
		return ProcessoDTO.toDTO(processo);
	}
	
	public Processo criar(Processo processo) {
		return processoRepository.save(processo);
	}

	public Processo alterar(Long processoId, Processo processoRequest) {
		return processoRepository.findById(processoId).map(processo -> {
			processo.setNumero(processoRequest.getNumero());
			processo.setDescricao(processoRequest.getDescricao());
			processo.setStatusParecer(processoRequest.getStatusParecer());
			processo.setUsuariosParecer(processoRequest.getUsuariosParecer());
			return processoRepository.save(processo);
		}).orElseThrow(() -> new RuntimeException("Processo não encontrado!"));
	}

	public void deletar(Long processoId) {
		if (!processoRepository.existsById(processoId)) {
			throw new RuntimeException("Processo não encontrado!");
		}
		processoRepository.findById(processoId).map(processo -> {
			processoRepository.delete(processo);
			return true;
		}).orElseThrow(() -> new RuntimeException("Processo não encontrado!"));
	}
	
	public List<ProcessoDTO> listarPendentesDeParecerPorUsuario(Long usuarioId) {
		return ProcessoDTO.toListDTO(processoRepository.findByStatusParecerAndUsuariosParecer_id(ParecerStatus.PENDENTE,usuarioId));
	}
	
	public Processo alterarStatusParecer(Long processoId ,ParecerStatus statusParecer) {
		return processoRepository.findById(processoId).map(processo -> {
			processo.setStatusParecer(statusParecer);;
			return processoRepository.save(processo);
		}).orElseThrow(() -> new RuntimeException("Processo não encontrado!"));
	}
	

}
