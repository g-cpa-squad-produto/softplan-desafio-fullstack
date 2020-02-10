package com.example.demo.repository;

import com.example.demo.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio spring data para entidade report
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByAutorId(Long id);

}
