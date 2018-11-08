package br.com.processo.prjdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.processo.prjdemo.model.Parecer;

/**
 * 
 * @author Guilherme Sena
 * Repositorio JPA para o objeto Parecer
 */
@Repository("parecerRepository")
public interface ParecerRepository extends JpaRepository<Parecer, Long> {
}
