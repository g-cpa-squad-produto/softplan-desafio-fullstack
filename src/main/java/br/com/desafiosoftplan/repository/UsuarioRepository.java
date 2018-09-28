package br.com.desafiosoftplan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.desafiosoftplan.model.Processo;
import br.com.desafiosoftplan.model.Usuario;

/**
 * Repositório de persistencia da entidade {@link Usuario}
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
   Usuario findByLogin(String login);

   @Query("SELECT u FROM Usuario u WHERE u.tipoUsuario = 3")
   public List<Usuario> findFinalizadores();
}
