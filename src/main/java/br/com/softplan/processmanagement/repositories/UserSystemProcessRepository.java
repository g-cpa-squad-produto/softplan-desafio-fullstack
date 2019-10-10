package br.com.softplan.processmanagement.repositories;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserSystemProcess;
import br.com.softplan.processmanagement.domain.UserSystemProcessId;
import br.com.softplan.processmanagement.repositories.helpers.UserSystemProcessRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSystemProcessRepository extends JpaRepository<UserSystemProcess, UserSystemProcessId>, UserSystemProcessRepositoryQueries {
    List<UserSystemProcess> findAllByUserSystemProcessIdProcessAndTextNotNull(Process process);
    List<UserSystemProcess> findAllByUserSystemProcessIdUserSystemAndTextNotNull(UserSystem userSystem);
}
