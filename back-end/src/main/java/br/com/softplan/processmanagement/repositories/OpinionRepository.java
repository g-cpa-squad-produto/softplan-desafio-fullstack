package br.com.softplan.processmanagement.repositories;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.Opinion;
import br.com.softplan.processmanagement.repositories.helpers.OpinionRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long>, OpinionRepositoryQueries {
    List<Opinion> findAllByProcessAndTextNotNull(Process process);

    List<Opinion> findAllByUserSystemAndTextNotNull(UserSystem userSystem);

    List<Opinion> findAllByProcess(Process process);
}
