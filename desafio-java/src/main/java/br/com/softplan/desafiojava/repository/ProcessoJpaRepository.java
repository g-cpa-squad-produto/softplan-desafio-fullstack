package br.com.softplan.desafiojava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.softplan.desafiojava.entity.Processo;

public interface ProcessoJpaRepository extends JpaRepository<Processo, Long> {

	  @Query("select P from Processo P inner join P.finalizadores U where P.pendente = 'S' and U.id = ?1")
	  List<Processo> findByUsuario(Long id);
}
