package com.softplan.processesapi.domain.process.repository;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.models.ProcessUser;
import com.softplan.processesapi.domain.user.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessUserRepository extends CrudRepository<ProcessUser, Long> {
    @Query("select pu from ProcessUser pu where pu.user = :user AND pu.process = :process")
    Optional<ProcessUser> findOneByUserAndProcess(@Param("user") User user, @Param("process") Process process);
}
