package br.com.softplan.backend.triador.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import br.com.softplan.backend.triador.model.ProcessoModel;

@Component
public interface ProcessoRepository extends MongoRepository<ProcessoModel, String>  {
}
