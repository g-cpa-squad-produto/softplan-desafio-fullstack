package fmreina.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import fmreina.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
