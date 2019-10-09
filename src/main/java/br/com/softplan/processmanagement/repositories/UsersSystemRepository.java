package br.com.softplan.processmanagement.repositories;

import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserType;
import br.com.softplan.processmanagement.repositories.helpers.UsersSystemRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersSystemRepository extends JpaRepository<UserSystem, Long>, UsersSystemRepositoryQueries {
    Optional<UserSystem> findByEmail(String email);
    List<UserSystem> findAllByType(UserType type);
    Boolean existsByEmail(String email);
}