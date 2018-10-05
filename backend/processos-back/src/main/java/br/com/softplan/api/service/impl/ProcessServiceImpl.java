package br.com.softplan.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import br.com.softplan.api.entity.Process;
import br.com.softplan.api.repository.ProcessRepository;
import br.com.softplan.api.service.ProcessService;
import br.com.softplan.arq.service.AbstractServiceImpl;
/**
 * Implementação dos métodos responsáveis por gerenciar os processos do sistema
 * @author Marco
 *
 */
@Service
public class ProcessServiceImpl extends AbstractServiceImpl<Process, Long> implements ProcessService {

	@Autowired
	private ProcessRepository processRepository; 
	
	public Page<Process> findByNumber(int page, int count, String number){
		Pageable pages = new PageRequest(page, count);
		return this.processRepository.findByNumber(number, pages);
	}
	
	@Override
	public Process findByNumber(String number) {
		return this.processRepository.findByNumber(number);
	}

	@Override
	public PagingAndSortingRepository<Process, Long> getRepository() {
		return this.processRepository;
	}
}
