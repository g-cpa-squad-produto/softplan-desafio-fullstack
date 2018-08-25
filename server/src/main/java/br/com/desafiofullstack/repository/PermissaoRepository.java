/**
 * 
 */
package br.com.desafiofullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafiofullstack.domain.Permissao;

/**
 * @author Delano Jr
 *
 */
@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
