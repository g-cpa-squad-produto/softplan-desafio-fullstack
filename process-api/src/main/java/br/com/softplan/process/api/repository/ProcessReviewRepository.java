package br.com.softplan.process.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.softplan.process.api.entity.ProcessReview;

public interface ProcessReviewRepository extends MongoRepository<ProcessReview, String> {

	Iterable<ProcessReview> findByProcessIdOrderByDateReviewDesc(String processId);
}
