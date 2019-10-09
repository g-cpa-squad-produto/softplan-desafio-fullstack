package br.com.softplan.processmanagement.repositories;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.User;
import br.com.softplan.processmanagement.domain.UserProcess;
import br.com.softplan.processmanagement.domain.UserProcessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProcessRepository extends JpaRepository<UserProcess, UserProcessId> {
    Optional<UserProcess> findUserProcessesByUserProcessIdUserAndUserProcessIdProcess(User user, Process process);
}
