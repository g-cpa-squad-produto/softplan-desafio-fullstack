package com.challenge.api.services;

import java.util.List;


import com.challenge.api.documents.User;

public interface UserService {
	List<User> listAll();
	
	User listByID(String id);
	
	User create(User user);
	
	User update(User user);
	
	void remove(String id);
	
}
