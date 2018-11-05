package com.process.processmanagerapi.repository;

import com.process.processmanagerapi.entity.Process;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProcessRepository extends CrudRepository<Process, Long> {

    Process findByProcessNumber(final int processNumber);

    List<Process> findAll();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM PROCESS_AUTHORIZED_USERS u WHERE u.AUTHORIZED_USERS_NAME = :userName", nativeQuery = true)
    void deleteUserAuthorizedToGiveOpinionByUserName(final @Param("userName") String userName);
}
