package br.com.softplan.desafiojava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.softplan.desafiojava.entity.Parecer;

public interface ParecerJpaRepository extends JpaRepository<Parecer, Long> {

	  @Query("select PA from Parecer PA inner join PA.processo PR where PR.id = ?1")
	  List<Parecer> findByProcesso(Long id);
}
