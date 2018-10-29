package br.com.evaluation.process.manager.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.evaluation.process.manager.domain.RoleType;
import br.com.evaluation.process.manager.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
	
	public Iterable<User> findByRole(RoleType role);
	
	public User findByLoginAndPasswordAndStatus(String login, String password, boolean status);
}
