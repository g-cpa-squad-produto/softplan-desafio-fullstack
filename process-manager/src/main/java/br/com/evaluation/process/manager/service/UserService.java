package br.com.evaluation.process.manager.service;

import br.com.evaluation.process.manager.domain.RoleType;
import br.com.evaluation.process.manager.entity.User;

public interface UserService {

	public User save(User u);
	
	public Iterable<User> findAll();
	
	public void delete(Long id);
	
	public Iterable<User> findByRole(RoleType role);
	
	public User findByLoginAndPassword(String login, String password);
}
