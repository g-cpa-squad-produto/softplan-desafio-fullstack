package com.softplan.processesapi.domain.process.repository;

import com.softplan.processesapi.domain.process.models.Process;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends CrudRepository<Process, Long> {
}
