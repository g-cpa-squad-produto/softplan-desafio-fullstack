package com.softplan.thiagobernardo.parecer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplan.thiagobernardo.exception.NaoEncontradoException;
import com.softplan.thiagobernardo.exception.ParecerProcessoException;
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
		return parecerRepository.findById(parecerId).orElseThrow(() -> new NaoEncontradoException("Parecer não encontrado!"));
	}
	
	public Parecer criar(Parecer parecer) {
		return parecerRepository.save(parecer);
	}

	/**
	 * Metodo altera somente os atributos titulo e descricao
	 * @param parecerId
	 * @param parecerRequest
	 * @return
	 */
	public Parecer alterar(Long parecerId, Parecer parecerRequest) {
		return parecerRepository.findById(parecerId).map(parecer -> {
			parecer.setTitulo(parecerRequest.getTitulo());
			parecer.setDescricao(parecerRequest.getDescricao());
			return parecerRepository.save(parecer);
		}).orElseThrow(() -> new NaoEncontradoException("Parecer não encontrado!"));
	}

	public void deletar(Long parecerId) {
		if (!parecerRepository.existsById(parecerId)) {
			throw new NaoEncontradoException("Parecer não encontrado!");
		}
		parecerRepository.findById(parecerId).map(parecer -> {
			parecerRepository.delete(parecer);
			return true;
		}).orElseThrow(() -> new NaoEncontradoException("Parecer não encontrado!"));
	}
	
	/**
	 * Metodo verifica se existe um parecer para o processo, caso não exista
	 * o parecer e salvo e o status do parecer no processo e atualizado
	 * @param parecer
	 * @return
	 * @throws ParecerProcessoException
	 */
	@Transactional
	public Parecer salvarParecerProcesso(Parecer parecer) throws Exception {
		Parecer existeParecer = parecerRepository.findByProcesso_id(parecer.getProcesso().getId());
		if(existeParecer==null) {
			processoService.alterarStatusParecer(parecer.getProcesso().getId(), ParecerStatus.CONCLUIDO);
			return criar(parecer);
		}else {
			throw new ParecerProcessoException();
		}
	}

}
