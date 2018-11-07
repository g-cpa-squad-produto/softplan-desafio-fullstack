package br.com.processo.prjdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.processo.prjdemo.model.Processo;

/**
 * 
 * @author Guilherme Sena
 * Repositorio JPA para o objeto Processo
 */
@Repository("processoRepository")
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

}
