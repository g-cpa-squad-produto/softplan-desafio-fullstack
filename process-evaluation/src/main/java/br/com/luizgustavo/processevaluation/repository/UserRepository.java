package br.com.luizgustavo.processevaluation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizgustavo.processevaluation.model.User;
import br.com.luizgustavo.processevaluation.model.enums.RoleUser;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByLogin(String login);
	List<User> findByRole(RoleUser role);
}
