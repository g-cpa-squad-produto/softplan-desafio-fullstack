package br.com.softplan.processmanagement.repositories;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserSystemProcess;
import br.com.softplan.processmanagement.domain.UserSystemProcessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSystemProcessRepository extends JpaRepository<UserSystemProcess, UserSystemProcessId> {
    Optional<UserSystemProcess> findUserSystemProcessByUserSystemProcessIdUserSystemAndUserSystemProcessIdProcess(UserSystem userSystem, Process process);
}
