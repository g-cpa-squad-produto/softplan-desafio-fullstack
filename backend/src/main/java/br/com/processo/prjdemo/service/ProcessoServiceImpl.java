package br.com.processo.prjdemo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processo.prjdemo.model.Parecer;
import br.com.processo.prjdemo.model.Processo;
import br.com.processo.prjdemo.repository.ParecerRepository;
import br.com.processo.prjdemo.repository.ProcessoRepository;

/**
 * 
 * @author Guilherme Sena
 * Fachada de servico para o objeto processo e seus filhos
 *
 */
@Service("processoService")
public class ProcessoServiceImpl implements ProcessoService {
		
	@Autowired
	ProcessoRepository processoRepository;
	
	@Autowired
	ParecerRepository parecerRepository;

	@Override
	public Processo getProcessoById(Long id) {
		return processoRepository.findById(id).get();
	}

	/**
	 * retorna todos o processos cadastrados
	 */
	@Override
	public List<Processo> getTodosProcessos() {
		return processoRepository.findAll();
	}

	/**
	 * retorna processo com o id salvo
	 */
	@Override
	public Processo salvarProcesso(Processo processo) {
		if(processo.getDataCriacao()== null){
			processo.setDataCriacao(new Date());
		}		
		return processoRepository.save(processo);
	}

	@Override
	public Parecer salvarProcessoParecer(Parecer parecer) {
		if(parecer.getDataCriacao() == null){
			parecer.setDataCriacao(new Date());
		}
		return parecerRepository.save(parecer);
	}
}
