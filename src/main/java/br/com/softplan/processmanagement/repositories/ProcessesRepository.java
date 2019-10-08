package br.com.softplan.processmanagement.repositories;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessesRepository extends JpaRepository<Process, Long> {
    List<Process> findAllByCreator(User user);
}
