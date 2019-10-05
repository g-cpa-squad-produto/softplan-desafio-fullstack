package com.isadora.backendapi.repositories;

import com.isadora.backendapi.domain.Processo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends CrudRepository<Processo, Long>{
}
