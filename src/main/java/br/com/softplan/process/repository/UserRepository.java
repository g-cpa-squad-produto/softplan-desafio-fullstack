package br.com.softplan.process.repository;

import br.com.softplan.process.model.Profile;
import br.com.softplan.process.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    List<User> findByIdIn(List<Long> ids);
    List<User> findAllByProfiles(List<Profile> profiles);
}