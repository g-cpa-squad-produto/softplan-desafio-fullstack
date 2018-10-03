package br.com.softplan.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.softplan.api.entity.ProfileEnum;
import br.com.softplan.api.entity.User;

/**
 * Classe responsável pelo acesso aos dados do usuário 
 * @author Marco
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>  {
	
	/**
	 * Realiza a busca de um usuário pelo email
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

	/**
	 * Busca os usuários de um determinado perfil
	 * @param profile
	 * @return
	 */
	List<User> findByProfile(ProfileEnum profile);

}
