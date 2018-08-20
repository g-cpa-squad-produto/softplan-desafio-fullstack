package com.processos.luiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.processos.luiz.models.Processo;

public interface ProcessoRepository extends JpaRepository<Processo,Long>{
	@Query(value = "select p.* from processo p "
			+ " join processo_usuario pu on pu.processo_codigo=p.codigo "
			+ " where pu.usuario_codigo =:usuarioId",nativeQuery=true)
	public List<Processo> listarProcessosFinalizador(@Param("usuarioId") Long usuarioId);
	
}
