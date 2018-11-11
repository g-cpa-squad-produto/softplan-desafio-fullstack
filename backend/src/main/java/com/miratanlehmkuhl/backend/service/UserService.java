package com.miratanlehmkuhl.backend.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.miratanlehmkuhl.backend.dto.UserNewDTO;
import com.miratanlehmkuhl.backend.model.User;

public interface UserService extends UserDetailsService {
	
	List<User> listAll();

	User save(User user);

	User findByEmail(String email);

	void registration(UserNewDTO user);

}
