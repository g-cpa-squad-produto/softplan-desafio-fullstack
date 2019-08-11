package br.com.ramonbarros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ramonbarros.entity.Parecer;
import br.com.ramonbarros.entity.Processo;
import br.com.ramonbarros.repository.ParecerRepository;
import br.com.ramonbarros.repository.ProcessoRepository;

@Service
public class ParecerService {

	@Autowired
	private ProcessoRepository processoRepository;
	
	@Autowired
	private ParecerRepository parecerRepository;

	public List<Parecer> buscarPorProcesso(Long idProcesso) {
		Processo processo = processoRepository.findById(idProcesso).get();
		List<Parecer> lista = processo.getListaParecer();
		return lista;
	}

	public Parecer salvar(Parecer parecer) {
		return parecerRepository.save(parecer);
	}

	
	
}
