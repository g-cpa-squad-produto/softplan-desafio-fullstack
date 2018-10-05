package br.com.softplan.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import br.com.softplan.api.entity.ProfileEnum;
import br.com.softplan.api.entity.User;
import br.com.softplan.api.repository.UserRepository;
import br.com.softplan.api.service.UserService;
import br.com.softplan.arq.service.AbstractServiceImpl;
/**
 * Implementação dos métodos responsáveis por gerenciar os usuários do sistema
 * @author Marco
 *
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User, Long> implements UserService{

	@Autowired
	private UserRepository userRepository; 

	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public List<User> findByProfile(ProfileEnum profile) {
		return this.userRepository.findByProfile(profile);
	}

	@Override
	public PagingAndSortingRepository<User, Long> getRepository() {
		return this.userRepository;
	}
}
