package br.com.softplan.backend.processo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProcessoRepository extends MongoRepository<ProcessoModel, String>  {
}
