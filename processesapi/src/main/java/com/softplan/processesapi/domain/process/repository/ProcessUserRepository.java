package com.softplan.processesapi.domain.process.repository;

import com.softplan.processesapi.domain.process.models.ProcessUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessUserRepository extends CrudRepository<ProcessUser, Long> {
}
