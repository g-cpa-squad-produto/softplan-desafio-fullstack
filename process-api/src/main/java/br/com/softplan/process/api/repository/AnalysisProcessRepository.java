package br.com.softplan.process.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.softplan.process.api.entity.AnalysisProcess;

public interface AnalysisProcessRepository extends MongoRepository<AnalysisProcess, String> {

	Iterable<AnalysisProcess> findByProcessIdOrderByDateDesc(String processId);
}
