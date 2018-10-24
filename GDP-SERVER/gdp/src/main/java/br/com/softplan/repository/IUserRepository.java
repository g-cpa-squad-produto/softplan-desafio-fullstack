package br.com.softplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.models.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	public User findByLogin(String login);
	public User findByLoginAndPassword(String login, String password);
}
