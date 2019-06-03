package com.pmanagement.pmanagementbackend.domain.repository;

import com.pmanagement.pmanagementbackend.domain.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to persist the {@link Process}
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {

}