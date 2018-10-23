package com.softplan.thiagobernardo.processo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softplan.thiagobernardo.processo.entity.Processo;
import com.softplan.thiagobernardo.util.ParecerStatus;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
	
	/**
	 * Retorna ums lista de processos com base no status do parecer e do id do usuario
	 * @param status
	 * @param usuarioId
	 * @return
	 */
	List<Processo> findByStatusParecerAndUsuariosPararecer_id(ParecerStatus status, Long usuarioId);

}
