package com.softplan.teste.process.repository;

import com.softplan.teste.process.model.Process;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
    Optional<Process> findById(Long processId);

    Page<Process> findByCreatedBy(Long userId, Pageable pageable);

    long countByCreatedBy(Long userId);

    List<Process> findByIdIn(List<Long> processIds);

    List<Process> findByIdIn(List<Long> processIds, Sort sort);

    @Query("SELECT p FROM Process p WHERE p.status = :status")
    Page<Process> findPendingProcesess(String status, Pageable pageable);
}