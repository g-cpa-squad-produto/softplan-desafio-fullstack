package fmreina.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import fmreina.app.model.Process;

public interface ProcessRepository extends JpaRepository<Process, Long> {

}
