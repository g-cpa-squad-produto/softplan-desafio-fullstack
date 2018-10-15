package br.com.softplan.process.repository;

import br.com.softplan.process.model.Process;
import br.com.softplan.process.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> findByUsers(List<User> users);
}