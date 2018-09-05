package br.com.softplan.process.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.softplan.process.api.entity.AnalysisProcess;
import br.com.softplan.process.api.entity.ChangeStatus;
import br.com.softplan.process.api.entity.Process;
import br.com.softplan.process.api.repository.AnalysisProcessRepository;
import br.com.softplan.process.api.repository.ChangeStatusRepository;
import br.com.softplan.process.api.repository.ProcessRepository;
import br.com.softplan.process.api.service.ProcessService;

@Service
public class ProcessServiceImpl implements ProcessService  {

	@Autowired
	private ProcessRepository processRepository;
	
	@Autowired
	private ChangeStatusRepository changeStatusRepository;
	
	@Autowired
	private AnalysisProcessRepository analysisProcessRepository;
	
	@Override
	public Process createOrUpdate(Process process) {
		return this.processRepository.save(process);
	}

	@Override
	public Process findById(String id) {
		return this.processRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		this.processRepository.delete(id);		
	}

	@Override
	public Page<Process> listProcess(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.processRepository.findAll(pages);
	}

	@Override
	public ChangeStatus createChangeStatus(ChangeStatus changeStatus) {
		return this.changeStatusRepository.save(changeStatus);
	}

	@Override
	public Iterable<ChangeStatus> listChangeStatus(String processId) {
		return this.changeStatusRepository.findByProcessIdOrderByDateChangeStatusDesc(processId);
	}
	
	@Override
	public AnalysisProcess createAnalysisProcess(AnalysisProcess analysisProcess) {
		return this.analysisProcessRepository.save(analysisProcess);
	}

	@Override
	public Iterable<AnalysisProcess> listAnalysisProcess(String processId) {
		return this.analysisProcessRepository.findByProcessIdOrderByDateDesc(processId);
	}	

	@Override
	public Page<Process> findByCurrentUser(int page, int count, String userId) {
		Pageable pages = new PageRequest(page, count);
		return this.processRepository.findByUserIdOrderByDateDesc(pages, userId);
	}

	@Override
	public Page<Process> findByParameters(int page, int count, String subject, String status, String priority) {
		Pageable pages = new PageRequest(page, count);
		return this.processRepository.findBySubjectIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(subject, status, priority, pages);
	}

	@Override
	public Page<Process> findByParametersAndCurrentUser(int page, int count, String subject, String status,
			String priority, String userId) {
		Pageable pages = new PageRequest(page, count);
		return this.processRepository.findBySubjectIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(subject, status, priority, pages);
	}

	@Override
	public Page<Process> findByNumber(int page, int count, Integer number) {
		Pageable pages = new PageRequest(page, count);		
		return this.processRepository.findByNumber(number, pages);
	}

	@Override
	public Iterable<Process> findAll() {
		return this.processRepository.findAll();
	}

	@Override
	public Page<Process> findByParameterAndAssignedUser(int page, int count, String subject, String status,
			String priority, String assignedUser) {
		Pageable pages = new PageRequest(page, count);		
		return this.processRepository.findBySubjectIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(subject, status, priority, pages);
	}
	
}
