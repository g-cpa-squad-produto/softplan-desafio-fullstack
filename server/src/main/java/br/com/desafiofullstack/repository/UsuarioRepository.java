/**
 * 
 */
package br.com.desafiofullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafiofullstack.domain.Usuario;

/**
 * @author Delano Jr
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
