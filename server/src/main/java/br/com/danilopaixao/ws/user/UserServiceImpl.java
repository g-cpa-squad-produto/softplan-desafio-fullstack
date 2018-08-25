package br.com.danilopaixao.ws.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.danilopaixao.ws.user.api.request.UserRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository repository;
	
	@Override
	public UserResponse save(UserRequest userRequest) {
		log.info("save user");
		User user = User.builder()
				.name(userRequest.getName())
				.login(userRequest.getLogin())
				.build();
		this.repository.save(user);
		return UserResponse
					.builder()
					.login(user.getLogin())
					.name(user.getName())
					.build();
		
	}
	
	@Override
	public UserResponse inativeUser(Long id) {
		User user = this.repository.findOne(id);
		//update user to set active = false
		return UserResponse.builder()
				.name(user.getName())
				.login(user.getLogin())
				.build();
	}

	@Override
	public UserResponse getById(Long id) {
		User user = this.repository.findOne(id);
		return UserResponse.builder()
				.name(user.getName())
				.login(user.getLogin())
				.build();
	}
	
	@Override
	public List<UserResponse> getByAllUsers() {
		return this.repository
				.findAll()
				.stream()
				.map(u -> UserResponse.builder()
								.name(u.getName())
								.login(u.getLogin())
								.build()
				).collect(Collectors.toList());		
	}
	
}
