package br.com.softplan.process.repository;

import br.com.softplan.process.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<Process, Long> {


}