package br.com.softplan.processmanagement.repositories;

import br.com.softplan.processmanagement.domain.Opinion;
import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OpinionsRepository extends JpaRepository<Opinion, Long> {
    Optional<Opinion> findByUserAndProcess(User user, Process process);

}
