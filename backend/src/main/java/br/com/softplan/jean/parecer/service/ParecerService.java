package br.com.softplan.jean.parecer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.jean.parecer.entity.Parecer;
import br.com.softplan.jean.parecer.repository.ParecerRepository;
import br.com.softplan.jean.processo.service.ProcessoService;

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
	public Parecer salvarParecerProcesso(Parecer parecer) throws Exception {
		Parecer existeParecer = parecerRepository.findByProcesso_id(parecer.getProcesso().getId());
		System.out.println("teste");;
		/*if(existeParecer==null) {
			processoService.alterarStatusParecer(parecer.getProcesso().getId(), ParecerStatus.CONCLUIDO);
			return criar(parecer);
		}else {
			throw new Exception("Já existe paracer para esse processo!");
		}
		*/
		return null;
	}

}
