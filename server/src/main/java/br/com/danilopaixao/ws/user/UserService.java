package br.com.danilopaixao.ws.user;

import java.util.List;

import br.com.danilopaixao.ws.user.api.request.UserRequest;

public interface UserService {

	UserResponse save(UserRequest user);
	UserResponse getById(Long id);
	List<UserResponse> getByAllUsers();
	UserResponse inativeUser(Long id);

}
