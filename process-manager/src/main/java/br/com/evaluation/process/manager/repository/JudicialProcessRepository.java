package br.com.evaluation.process.manager.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.evaluation.process.manager.entity.JudicialProcess;

@Repository
public interface JudicialProcessRepository extends CrudRepository<JudicialProcess, Long>  {

}
