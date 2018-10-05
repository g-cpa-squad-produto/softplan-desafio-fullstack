package br.com.softplan.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.softplan.api.entity.Process;
import br.com.softplan.arq.service.AbstractService;

/**
 * Interface responsável por concentrar todos os métodos padrão de gerenciamento de processos que ficará disponível para o controller.
 * @author Marco
 *
 */
@Component
public interface ProcessService extends AbstractService<Process, Long>{

	/**
	 * Realiza a busca de um processo pelo numero
	 * @param page
	 * @param count
	 * @param number
	 * @return
	 */
	public Page<Process> findByNumber(int page, int count, String number);
	
	/**
	 * Realiza a busca de um processo pelo numero
	 * @param page
	 * @param count
	 * @param number
	 * @return
	 */
	public Process findByNumber(String number);
}
