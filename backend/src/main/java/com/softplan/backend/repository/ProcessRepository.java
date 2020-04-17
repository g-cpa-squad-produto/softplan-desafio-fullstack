package com.softplan.backend.repository;

import com.softplan.backend.entity.Process;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository  extends JpaRepository<Process, Long> {
    Page<Process> findAll(Pageable pageable);
    Page<Process> findAllByAssignId(Pageable pageable, Long id);
}
