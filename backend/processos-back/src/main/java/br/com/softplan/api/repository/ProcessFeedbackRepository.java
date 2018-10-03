package br.com.softplan.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.softplan.api.entity.Process;
import br.com.softplan.api.entity.ProcessFeedback;
import br.com.softplan.api.entity.User;


/**
 * Classe responsável pelo acesso aos dados do usuário 
 * @author Marco
 *
 */
public interface ProcessFeedbackRepository extends PagingAndSortingRepository<ProcessFeedback, Long>  {
	
	Page<ProcessFeedback> findByFinalizator(User finalizator, Pageable pages);

	Page<ProcessFeedback> findByProcess(Process process, Pageable pages);

	ProcessFeedback findByProcessAndFinalizator(Process process, User finalizator);
	
}
