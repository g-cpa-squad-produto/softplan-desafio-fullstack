package com.isadora.backendapi.repositories;

import com.isadora.backendapi.domain.Processo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends CrudRepository<Processo, Long>{

    @Query("SELECT p FROM Processo p left join Parecer e on p.id=e.processo where e.processo is null")
    Iterable<Processo> findByParecerNull();
}
