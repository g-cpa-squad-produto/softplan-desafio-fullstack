package br.com.danilopaixao.ws.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository repository;
	
	@Override
	public UserResponse save(UserRequest userRequest) {
		log.info("save user => " + userRequest);
		User user = User.builder()
				.name(userRequest.getName())
				.login(userRequest.getLogin())
				.password(userRequest.getPassword())
				.profile(userRequest.getProfile())
				.status(userRequest.getStatus())
				.build();
		this.repository.save(user);
		return UserResponse
					.builder()
					.login(user.getLogin())
					.name(user.getName())
					.profile(user.getProfile())
					.status(user.getStatus())
					.build();
		
	}
	
	@Override
	public UserResponse save(Long id, UserRequest userRequest) {
		log.info("save user", userRequest);
		User user = this.repository.findOne(id);
		user.setName(userRequest.getName());
		user.setLogin(userRequest.getLogin());
		user.setProfile(userRequest.getProfile());
		user.setStatus(userRequest.getStatus());
		this.repository.save(user);
		return UserResponse
					.builder()
					.id(user.getId())
					.login(user.getLogin())
					.name(user.getName())
					.profile(user.getProfile())
					.status(user.getStatus())
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
				.id(user.getId())
				.name(user.getName())
				.login(user.getLogin())
				.profile(user.getProfile())
				.status(user.getStatus())
				.build();
	}
	
	@Override
	public User getUserById(Long id) {
		return this.repository.findOne(id);
	}
	
	@Override
	public List<UserResponse> getByAllUsers() {
		return this.repository
				.findAll()
				.stream()
				.map(u -> UserResponse.builder()
								.id(u.getId())
								.name(u.getName())
								.login(u.getLogin())
								.profile(u.getProfile())
								.status(u.getStatus())
								.build()
				).collect(Collectors.toList());		
	}
	
}
