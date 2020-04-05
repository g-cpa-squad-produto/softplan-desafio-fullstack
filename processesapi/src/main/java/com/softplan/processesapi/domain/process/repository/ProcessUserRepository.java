package com.softplan.processesapi.domain.process.repository;

import com.softplan.processesapi.domain.process.models.Process;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessUserRepository extends CrudRepository<Process, Long> {
}
