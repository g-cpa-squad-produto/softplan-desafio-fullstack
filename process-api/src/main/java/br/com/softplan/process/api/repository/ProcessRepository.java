package br.com.softplan.process.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.softplan.process.api.entity.Process;

public interface ProcessRepository extends MongoRepository<Process, String> {

	Page<Process> findByUserIdOrderByDateDesc(Pageable pages, String userId);

	Page<Process> findBySubjectIgnoreCaseContainingAndStatusContainingAndPriorityContainingOrderByDateDesc(
			String subject, String status, String priority, Pageable pages);

	Page<Process> findBySubjectIgnoreCaseContainingAndStatusContainingAndPriorityContainingAndUserIdOrderByDateDesc(
			String subject, String status, String priority, String userId, Pageable pages);

	Page<Process> findBySubjectIgnoreCaseContainingAndStatusContainingAndPriorityContainingAndAssignedUserOrderByDateDesc(
			String subject, String status, String priority, String userId, Pageable pages);
	
	Page<Process> findByNumber(Integer number, Pageable pages);
}
