package br.com.softplan.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.softplan.api.entity.Process;
import br.com.softplan.api.repository.ProcessRepository;
import br.com.softplan.api.service.ProcessService;
/**
 * Implementação dos métodos responsáveis por gerenciar os usuários do sistema
 * @author Marco
 *
 */
@Component
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private ProcessRepository processRepository; 

	public Process createOrUpdate(Process Process) {
		return this.processRepository.save(Process);
	}

	public Process findById(Long id) {
		return this.processRepository.findOne(id);
	}

	public void delete(Long id) {
		this.processRepository.delete(id);
	}

	public Page<Process> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.processRepository.findAll(pages);
	}
	
	public Page<Process> findByNumber(int page, int count, String number){
		Pageable pages = new PageRequest(page, count);
		return this.processRepository.findByNumber(number, pages);
	}
}
