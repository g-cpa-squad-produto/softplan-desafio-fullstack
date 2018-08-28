package br.com.danilopaixao.ws.user;

import java.util.List;

public interface UserService {

	UserResponse save(UserRequest user);
	UserResponse save(Long id, UserRequest user);
	UserResponse getById(Long id);
	List<UserResponse> getByAllUsers();
	UserResponse inativeUser(Long id);
	User getUserById(Long id);

}
