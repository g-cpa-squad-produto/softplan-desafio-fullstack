package br.com.softplan.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

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

}
