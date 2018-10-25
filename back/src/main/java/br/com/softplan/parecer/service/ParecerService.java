package br.com.softplan.parecer.service;

import java.util.List;

import br.com.softplan.parecer.modelos.Parecer;

/**
 * @author emanuel
 *
 */
public interface ParecerService {

	public Parecer incluirParecer(Parecer parecer);

	public List<Parecer> listarParecerPorProcesso(Integer idProcesso);
}
