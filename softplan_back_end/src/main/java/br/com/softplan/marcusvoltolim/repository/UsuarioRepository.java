package br.com.softplan.marcusvoltolim.repository;

import br.com.softplan.marcusvoltolim.enums.Permissao;
import br.com.softplan.marcusvoltolim.model.Usuario;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends EntityRepository<Usuario> {
	
	
	List<Usuario> findAllByLoginLikeOrEmailLikeOrNomeCompletoLike(String login, String email, String nome);
	
	List<Usuario> findAllByPermissao(@Param("permissao") Permissao permissao);
	
	Usuario findByLogin(String login);
	
}
