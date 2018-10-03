package br.com.softplan.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.softplan.api.entity.ProfileEnum;
import br.com.softplan.api.entity.User;

/**
 * Interface responsável por concentrar todos os método padrão de gerenciamento de usuário que ficará disponível para o controller.
 * @author Marco
 *
 */
@Component
public interface UserService {
	
	/**
	 * Busca o usuário por email
	 * @param email
	 * @return
	 */
	User findByEmail(String email); 
	
	/**
	 * Cria o usuário ou altera, considerando se o id foi informado ou não.
	 * @param user
	 * @return
	 */
	User createOrUpdate(User user);
	
	/**
	 * Busca o usuário pelo ID
	 * @param id
	 * @return
	 */
	User findById(Long id);
	
	/**
	 * Remove o usuário
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * Busca todos os usuários paginado.
	 * @param page
	 * @param count
	 * @return
	 */
	Page<User> findAll(int page, int count);

	/**
	 * Busca os usuários por um perfil
	 * @param profile
	 * @return
	 */
	List<User> findByProfile(ProfileEnum profile);
}
