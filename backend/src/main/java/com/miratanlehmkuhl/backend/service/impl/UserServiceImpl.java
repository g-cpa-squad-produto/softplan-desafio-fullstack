package com.miratanlehmkuhl.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.miratanlehmkuhl.backend.dto.UserNewDTO;
import com.miratanlehmkuhl.backend.model.User;
import com.miratanlehmkuhl.backend.repository.UserRepository;
import com.miratanlehmkuhl.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> listAll() {
		return (List<User>) repository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("user.not.found");
		}
		return new User(user.getEmail(), 
						new BCryptPasswordEncoder().encode(user.getPassword()),
	        			user.getAuthorities());
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	/*@Override
	public void registration(UserNewDTO user) {
		return repository.save(user);
	}*/

}