package com.thiagoag.wsmongo.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.thiagoag.wsmongo.domain.LegalProcess;

@Repository
public interface LegalProcessRepository extends MongoRepository<LegalProcess, String>{

	List<LegalProcess> findByProcessNumberContainingIgnoreCase(String text);

	@Query("{ 'processNumber': { $regex: ?0, $options: 'i' } }")
	List<LegalProcess> findByProcNumQuery(String text);
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: {$lte: ?2} }, { $or: [ { 'processNumber': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'decision.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<LegalProcess> fullSearch(String text, Date minDate, Date maxDate);
}
