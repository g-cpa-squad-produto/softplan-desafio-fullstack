package com.example.demo.repository;

import com.example.demo.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

/**
 * Repositorio spring data para entidade processo
 */
@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {

    List<Process> findAllByAutorId(Long id);
}
