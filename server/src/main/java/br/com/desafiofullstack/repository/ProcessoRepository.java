/**
 * 
 */
package br.com.desafiofullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafiofullstack.domain.Processo;

/**
 * @author Delano Jr
 *
 */
@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

}
