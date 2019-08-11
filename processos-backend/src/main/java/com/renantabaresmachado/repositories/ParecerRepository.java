package com.renantabaresmachado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renantabaresmachado.domains.Parecer;

@Repository
public interface ParecerRepository extends JpaRepository<Parecer, Integer>{
	

}
