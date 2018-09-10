package br.com.softplan.process.api.service;

import org.springframework.data.domain.Page;

import br.com.softplan.process.api.entity.Process;
import br.com.softplan.process.api.entity.ProcessReview;

public interface ProcessService {

	Process createOrUpdate(Process process);

	Process findById(String id);

	void delete(String id);

	Page<Process> listProcess(int Page, int count);

	ProcessReview createProcessReview(ProcessReview processReview);
		
	Iterable<ProcessReview> listProcessReview(String processId);
	
	Page<Process> findByCurrentUser(int page, int count, String userId);

	Page<Process> findByParameters(int page, int count, String subject, String status, String priority);

	Page<Process> findByParametersAndCurrentUser(int page, int count, String subject, String status, String priority,
			String userId);

	Page<Process> findByNumber(int page, int count, Integer number);

	Iterable<Process> findAll();
		
	Page<Process> findByParameterAndAssignedUser(int page, int count, String subject, String status, String priority,
			String assignedUser);
}
