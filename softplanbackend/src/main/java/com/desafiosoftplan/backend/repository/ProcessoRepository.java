package com.desafiosoftplan.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafiosoftplan.backend.enums.Status;
import com.desafiosoftplan.backend.model.Processo;
import com.desafiosoftplan.backend.model.Situacao;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo,Long> {
	
//	@Modifying
//	@Query(value="	SELECT	processo "
//				+ "	FROM	com.desafiosoftplan.backend.model.Processo processo "
//				+ "	WHERE	processo.status = :status ")
//	public List<Processo> getProcessosComSituacao(@Param("status") Situacao status);

//	@Modifying
//	@Query(value="	SELECT	processo "
//				+ "	FROM	com.desafiosoftplan.backend.model.Processo processo "
//				+ "	WHERE	processo.status.id = :statusId ")
//	public List<Processo> getProcessosComSituacao(@Param("statusId") Long statusId);
	
	@Modifying
	@Query(value="	SELECT	processo "
				+ "	FROM	com.desafiosoftplan.backend.model.Processo processo "
				+ "	WHERE	processo.status = :status ")
	public List<Processo> getProcessosPorSituacao(@Param("status") Status status);
	
//	@Modifying
//	@Query(value="	SELECT	processo "
//				+ "	FROM	com.desafiosoftplan.backend.model.Processo processo "
//				+ "	WHERE	processo.usuarioParecer = :a ")
//	public List<Processo> getProcessosPorAvaliador(@Param("a") String avaliador);
}
