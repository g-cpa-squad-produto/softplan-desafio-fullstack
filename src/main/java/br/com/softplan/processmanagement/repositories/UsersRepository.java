package br.com.softplan.processmanagement.repositories;

import br.com.softplan.processmanagement.domain.User;
import br.com.softplan.processmanagement.domain.UserType;
import br.com.softplan.processmanagement.repositories.helpers.UsersRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>, UsersRepositoryQueries {
    Optional<User> findByEmail(String email);
    List<User> findAllByType(UserType type);
}