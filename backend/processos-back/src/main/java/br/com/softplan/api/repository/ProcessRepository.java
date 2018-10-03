package br.com.softplan.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.softplan.api.entity.Process;


/**
 * Classe responsável pelo acesso aos dados do usuário 
 * @author Marco
 *
 */
public interface ProcessRepository extends PagingAndSortingRepository<Process, Long>  {
	
	Page<Process> findByNumber(String number,Pageable pages);
	
}
