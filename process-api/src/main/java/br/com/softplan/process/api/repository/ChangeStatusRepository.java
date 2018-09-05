package br.com.softplan.process.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.softplan.process.api.entity.ChangeStatus;

public interface ChangeStatusRepository extends MongoRepository<ChangeStatus, String> {

	Iterable<ChangeStatus> findByProcessIdOrderByDateChangeStatusDesc(String processId);

}
