package com.luanrubensf.challenge.repository;

import com.luanrubensf.challenge.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAndEnabledIsTrue(String email);

    List<User> findAllBy();

    User findUserById(Long id);

    boolean existsByEmailAndIdIsNot(String email, Long id);

    boolean existsByEmail(String email);

    @Query("select u from User u where u.role = 3 and u.id not in (select p.user from Parecer p where p.processo.id = :processoId)")
    List<User> findVinculaveisQuery(@Param("processoId") Long id);
}
