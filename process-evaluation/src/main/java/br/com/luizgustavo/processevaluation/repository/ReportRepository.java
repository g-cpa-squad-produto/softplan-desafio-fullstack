package br.com.luizgustavo.processevaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizgustavo.processevaluation.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
