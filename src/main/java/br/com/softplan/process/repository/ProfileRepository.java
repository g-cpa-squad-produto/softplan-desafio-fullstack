package br.com.softplan.process.repository;

import br.com.softplan.process.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findByIdIn(List<Long> ids);
    Profile findByName(String name);
}