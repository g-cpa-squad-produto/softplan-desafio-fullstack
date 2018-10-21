package com.softplan.thiagobernardo.parecer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softplan.thiagobernardo.parecer.entity.Parecer;

@Repository
public interface ParecerRepository extends JpaRepository<Parecer, Long> {
	
	List<Parecer> findByProcessoId(Long id);
	
	List<Parecer> findByUsuarioId(Long id);
	
}
