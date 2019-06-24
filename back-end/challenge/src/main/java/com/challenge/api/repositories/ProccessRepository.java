package com.challenge.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.challenge.api.documents.Proccess;

public interface ProccessRepository extends MongoRepository<Proccess, String> {

}
