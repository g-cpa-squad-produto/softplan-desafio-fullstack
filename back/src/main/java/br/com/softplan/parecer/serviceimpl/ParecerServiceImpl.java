package br.com.softplan.parecer.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.parecer.modelos.Parecer;
import br.com.softplan.parecer.repository.ParecerRepository;
import br.com.softplan.parecer.service.ParecerService;

/**
 * @author emanuel
 *
 */
@Service
public class ParecerServiceImpl implements ParecerService {
	@Autowired
	private ParecerRepository repository;

	@Override
	public Parecer incluirParecer(Parecer parecer) {
		Parecer parecerSalvo = this.repository.save(parecer);
		return parecerSalvo;
	}

	@Override
	public List<Parecer> listarParecerPorProcesso(Integer idProcesso) {
		List<Parecer> pareceres = repository.listarParecerPorProcesso(idProcesso);
		return pareceres;
	}

}
