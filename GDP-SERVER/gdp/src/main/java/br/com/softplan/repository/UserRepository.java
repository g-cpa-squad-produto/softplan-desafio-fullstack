package br.com.softplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByLoginAndPassword(String login, String password);

}
