package com.example.demo.repository;

import com.example.demo.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio spring data para entidade report
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
