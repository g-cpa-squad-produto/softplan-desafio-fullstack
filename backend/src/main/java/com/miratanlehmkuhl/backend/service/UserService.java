package com.miratanlehmkuhl.backend.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.miratanlehmkuhl.backend.dto.ListUser;
import com.miratanlehmkuhl.backend.dto.UserNewDTO;
import com.miratanlehmkuhl.backend.dto.UserUpdateDTO;
import com.miratanlehmkuhl.backend.model.User;

public interface UserService extends UserDetailsService {
	
	List<User> listAll();

	User save(User user);

	User findByEmail(String email);

	ListUser findAll(Pageable pageable);

	User registration(UserNewDTO user);

	void update(UserUpdateDTO user);

}
