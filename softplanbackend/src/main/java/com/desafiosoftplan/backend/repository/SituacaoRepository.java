package com.desafiosoftplan.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiosoftplan.backend.model.Situacao;

@Repository
public interface SituacaoRepository extends JpaRepository<Situacao,Long> {
	
}
