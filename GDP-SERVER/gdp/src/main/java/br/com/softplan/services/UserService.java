package br.com.softplan.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.softplan.models.User;
import br.com.softplan.models.UserDTO;
import br.com.softplan.repository.IUserRepository;

@Service
public class UserService extends GenericService<User, Long> {

	@Autowired
	private IUserRepository repository;

	@Autowired
	TokenService tokenService;

	public UserDTO autenticate(String login, String password) {
		User user = this.repository.findByLoginAndPassword(login, password);
		
		if (user == null ) {
			return null;
		}

		UserDTO userDTO = UserDTO.builder().login(user.getLogin()).profile(user.getProfile())
				.token(this.tokenService.getToken()).build();

		return userDTO;
	}

	public User getUserByLogin(String login) {
		return this.repository.findByLogin(login);
	}

	@Override
	public void update(User t) {
		
		Optional<User> aux = this.repository.findById(t.getId());
		
		if (aux == null) {
			new UsernameNotFoundException("User Not Found");
		}
		
		t.setPassword(aux.get().getPassword());
		this.repository.save(t);
	}
}
