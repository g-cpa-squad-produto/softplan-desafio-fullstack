package br.com.softplan.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.softplan.api.entity.User;
import br.com.softplan.api.repository.UserRepository;
import br.com.softplan.api.service.UserService;
/**
 * Implementação dos métodos responsáveis por gerenciar os usuários do sistema
 * @author Marco
 *
 */
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository; 

	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	public User createOrUpdate(User user) {
		return this.userRepository.save(user);
	}

	public User findById(Long id) {
		return this.userRepository.findOne(id);
	}

	public void delete(Long id) {
		this.userRepository.delete(id);
	}

	public Page<User> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.userRepository.findAll(pages);
	}
}
