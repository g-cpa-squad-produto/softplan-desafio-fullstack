package br.com.softplan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.models.User;
import br.com.softplan.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User autenticate(User user) {
		return this.userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
	}
	
	public User getUserByLogin(String login) {
		return this.userRepository.findByLogin(login);
	}

}
