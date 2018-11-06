package br.com.softplan.jean.parecer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.jean.parecer.entity.Parecer;

@Repository
public interface ParecerRepository extends JpaRepository<Parecer, Long> {
	
	Parecer findByProcesso_id(Long processoId);
	
}
