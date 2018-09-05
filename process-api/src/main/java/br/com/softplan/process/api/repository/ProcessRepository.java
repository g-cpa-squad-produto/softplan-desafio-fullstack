package br.com.softplan.process.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.softplan.process.api.entity.Process;

public interface ProcessRepository extends MongoRepository<Process, String> {

	Page<Process> findByUserIdOrderByDateDesc(Pageable pages, String userId);

	Page<Process> findBySubjectIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(
			String subject, String status, String priority, Pageable pages);

	Page<Process> findBySubjectIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(
			String subject, String status, String priority, Pageable pages);

	Page<Process> findBySubjectIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(
			String subject, String status, String priority, Pageable pages);
	
	Page<Process> findByNumber(Integer number, Pageable pages);
}
