package com.miratanlehmkuhl.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.miratanlehmkuhl.backend.dto.ListUser;
import com.miratanlehmkuhl.backend.dto.UserNewDTO;
import com.miratanlehmkuhl.backend.dto.UserUpdateDTO;
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

	@Override
	public ListUser findAll(Pageable pageable) {
		PageImpl<User> search = repository.findAll(pageable);
		return new ListUser(search.getContent(), search.getTotalElements());
	}

	@Override
	public User registration(UserNewDTO user) {
		if (repository.existsByEmail(user.getEmail())) {
			throw new RestClientException("E-mail já cadastrado!"); 
		}
		return repository.save(new User(user));
	}

	@Override
	public void update(UserUpdateDTO user) {
		repository.save(new User(user));
	}

}