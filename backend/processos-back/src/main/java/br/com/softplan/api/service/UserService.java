package br.com.softplan.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.softplan.api.entity.ProfileEnum;
import br.com.softplan.api.entity.User;
import br.com.softplan.arq.service.AbstractService;

/**
 * Interface responsável por concentrar todos os método padrão de gerenciamento de usuário que ficará disponível para o controller.
 * @author Marco
 *
 */
@Component
public interface UserService extends AbstractService<User, Long> {
	
	/**
	 * Busca o usuário por email
	 * @param email
	 * @return
	 */
	User findByEmail(String email); 

	/**
	 * Busca os usuários por um perfil
	 * @param profile
	 * @return
	 */
	List<User> findByProfile(ProfileEnum profile);
}
