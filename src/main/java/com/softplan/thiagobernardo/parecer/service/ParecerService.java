package com.softplan.thiagobernardo.parecer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplan.thiagobernardo.parecer.entity.Parecer;
import com.softplan.thiagobernardo.parecer.repository.ParecerRepository;
import com.softplan.thiagobernardo.processo.service.ProcessoService;
import com.softplan.thiagobernardo.util.ParecerStatus;

@Service
public class ParecerService {
	
	@Autowired
	private ParecerRepository parecerRepository;
	
	@Autowired
	private ProcessoService processoService;
	
	public List<Parecer> listar() {
		return parecerRepository.findAll();
	}
	
	public Parecer trazer(Long parecerId) {
		return parecerRepository.findById(parecerId).orElseThrow(() -> new RuntimeException("Parecer não encontrado!"));
	}
	
	public Parecer criar(Parecer parecer) {
		return parecerRepository.save(parecer);
	}

	public Parecer alterar(Long parecerId, Parecer parecerRequest) {
		return parecerRepository.findById(parecerId).map(parecer -> {
			parecer.setTitulo(parecerRequest.getTitulo());
			parecer.setDescricao(parecerRequest.getDescricao());
			return parecerRepository.save(parecer);
		}).orElseThrow(() -> new RuntimeException("Parecer não encontrado!"));
	}

	public void deletar(Long parecerId) {
		if (!parecerRepository.existsById(parecerId)) {
			throw new RuntimeException("Parecer não encontrado!");
		}
		parecerRepository.findById(parecerId).map(parecer -> {
			parecerRepository.delete(parecer);
			return true;
		}).orElseThrow(() -> new RuntimeException("Parecer não encontrado!"));
	}
	
	@Transactional
	public Parecer salvarParecerProcesso(Parecer parecer) {
		Parecer parecerCriado = criar(parecer);
		processoService.alterarStatusParecer(parecer.getProcesso().getId(), ParecerStatus.CONCLUIDO);
		return parecerCriado;
	}

}
