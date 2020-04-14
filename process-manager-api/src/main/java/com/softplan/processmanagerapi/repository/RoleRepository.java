package com.softplan.processmanagerapi.repository;

import com.softplan.processmanagerapi.models.Role;
import com.softplan.processmanagerapi.models.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType roleName);
}