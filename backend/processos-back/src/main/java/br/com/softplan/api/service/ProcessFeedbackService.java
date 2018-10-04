package br.com.softplan.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.softplan.api.entity.Process;
import br.com.softplan.api.entity.ProcessFeedback;
import br.com.softplan.api.entity.User;

/**
 * Interface responsável por concentrar todos os métodos padrão de gerenciamento de perecer de processos que ficará disponível para o controller.
 * @author Marco
 *
 */
@Component
public interface ProcessFeedbackService {
	
	/**
	 * Cria o ProcessFeedback ou altera, considerando se o id foi informado ou não.
	 * @param user
	 * @return
	 */
	ProcessFeedback createOrUpdate(ProcessFeedback processFeedback);
	
	/**
	 * Busca o ProcessFeedback pelo ID
	 * @param id
	 * @return
	 */
	ProcessFeedback findById(Long id);
	
	/**
	 * Remove o ProcessFeedback
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * Busca todos paginado.
	 * @param page
	 * @param count
	 * @return
	 */
	Page<ProcessFeedback> findAll(int page, int count);
	
	/**
	 * Realiza a busca de um processo pelo finalizador e se está pendente de feedback
	 * @param page
	 * @param count
	 * @param number
	 * @return
	 */
	public Page<ProcessFeedback> findByFinalizator(int page, int count, User finalizator, boolean pending);
	
	/**
	 * Realiza a busco por processo
	 * @param page
	 * @param count
	 * @param process
	 * @return
	 */
	public Page<ProcessFeedback> findByProcess(int page, int count, Process process);
	
	/**
	 * Realiza a busco por processoe finalizador
	 * @param page
	 * @param count
	 * @param process
	 * @return
	 */
	public ProcessFeedback findByProcessAndFinalizator(Process process, User finalizator);
}
