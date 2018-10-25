package br.com.softplan.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.softplan.entity.Parecer;

public interface ParecerRepository extends MongoRepository<Parecer, String> {

}
