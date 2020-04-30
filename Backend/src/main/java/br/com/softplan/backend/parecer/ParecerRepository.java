package br.com.softplan.backend.parecer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ParecerRepository extends MongoRepository<ParecerModel, String> {
}
