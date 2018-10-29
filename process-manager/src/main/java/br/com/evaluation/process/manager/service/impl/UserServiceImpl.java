package br.com.evaluation.process.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evaluation.process.manager.domain.RoleType;
import br.com.evaluation.process.manager.entity.User;
import br.com.evaluation.process.manager.repository.UserRepository;
import br.com.evaluation.process.manager.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User u) {
		return userRepository.save(u);
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Iterable<User> findByRole(RoleType role) {
		return userRepository.findByRole(role);
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		return userRepository.findByLoginAndPasswordAndStatus(login, password, true);
	}

}
