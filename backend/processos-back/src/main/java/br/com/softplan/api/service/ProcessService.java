package br.com.softplan.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.softplan.api.entity.Process;

/**
 * Interface responsável por concentrar todos os métodos padrão de gerenciamento de processos que ficará disponível para o controller.
 * @author Marco
 *
 */
@Component
public interface ProcessService {
	
	/**
	 * Cria o Processo ou altera, considerando se o id foi informado ou não.
	 * @param user
	 * @return
	 */
	Process createOrUpdate(Process user);
	
	/**
	 * Busca o Processo pelo ID
	 * @param id
	 * @return
	 */
	Process findById(Long id);
	
	/**
	 * Remove o Processo
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * Busca todos os Processos paginado.
	 * @param page
	 * @param count
	 * @return
	 */
	Page<Process> findAll(int page, int count);
	
	/**
	 * Realiza a busca de um processo pelo numero
	 * @param page
	 * @param count
	 * @param number
	 * @return
	 */
	public Page<Process> findByNumber(int page, int count, String number);
}
