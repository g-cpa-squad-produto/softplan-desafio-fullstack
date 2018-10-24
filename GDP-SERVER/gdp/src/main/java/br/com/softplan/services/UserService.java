package br.com.softplan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.models.User;
import br.com.softplan.repository.IUserRepository;

@Service
public class UserService extends GenericService<User, Long> {

	@Autowired
	private IUserRepository repository;

	public User autenticate(User user) {
		return this.repository.findByLoginAndPassword(user.getLogin(), user.getPassword());
	}

	public User getUserByLogin(String login) {
		return this.repository.findByLogin(login);
	}

	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public User update(User t, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
