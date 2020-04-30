package br.com.softplan.backend.finalizador.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import br.com.softplan.backend.finalizador.model.ParecerModel;

@Component
public interface ParecerRepository extends MongoRepository<ParecerModel, String> {
}
