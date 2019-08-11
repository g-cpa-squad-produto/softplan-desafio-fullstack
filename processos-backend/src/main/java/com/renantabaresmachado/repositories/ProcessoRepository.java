package com.renantabaresmachado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renantabaresmachado.domains.Processo;;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Integer>{
	

}
