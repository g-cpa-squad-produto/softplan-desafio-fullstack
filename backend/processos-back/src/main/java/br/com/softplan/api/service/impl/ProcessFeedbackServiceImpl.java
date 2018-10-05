package br.com.softplan.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import br.com.softplan.api.entity.Process;
import br.com.softplan.api.entity.ProcessFeedback;
import br.com.softplan.api.entity.User;
import br.com.softplan.api.repository.ProcessFeedbackRepository;
import br.com.softplan.api.service.ProcessFeedbackService;
import br.com.softplan.arq.service.AbstractServiceImpl;;
/**
 * Implementação dos métodos responsáveis por gerenciar os feedbacks dos processos do sistema
 * @author Marco
 *
 */
@Service
public class ProcessFeedbackServiceImpl extends AbstractServiceImpl<ProcessFeedback, Long> implements ProcessFeedbackService {

	@Autowired
	private ProcessFeedbackRepository processRepository; 

	public Page<ProcessFeedback> findByFinalizator(int page, int count, User finalizator, boolean pending){
		Pageable pages = new PageRequest(page, count);
		if(pending) {
			return this.processRepository.findByFinalizatorAndFeedbackIsNull(finalizator, pages);
		}else {
			return this.processRepository.findByFinalizator(finalizator, pages);
		}
	}
	
	public Page<ProcessFeedback> findByProcess(int page, int count, Process process){
		Pageable pages = new PageRequest(page, count);
		return this.processRepository.findByProcess(process, pages);
	}
	
	public ProcessFeedback findByProcessAndFinalizator(Process process, User finalizator){
		return this.processRepository.findByProcessAndFinalizator(process, finalizator);
	}

	@Override
	public PagingAndSortingRepository<ProcessFeedback, Long> getRepository() {
		return this.processRepository;
	}
}
