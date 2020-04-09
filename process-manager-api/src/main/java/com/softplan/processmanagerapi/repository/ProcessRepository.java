package com.softplan.processmanagerapi.repository;

import com.softplan.processmanagerapi.models.Process;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessRepository extends JpaRepository<Process, Long> {
    Optional<Process> findById(Long processId);

}
