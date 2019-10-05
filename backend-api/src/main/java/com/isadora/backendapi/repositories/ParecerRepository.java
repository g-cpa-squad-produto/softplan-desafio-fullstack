package com.isadora.backendapi.repositories;

import com.isadora.backendapi.domain.Parecer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParecerRepository extends CrudRepository<Parecer, Long> {
}
