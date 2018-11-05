package br.com.softplan.jean.processo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.jean.processo.entity.Processo;
import br.com.softplan.jean.util.ParecerStatus;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
	
	List<Processo> findByStatusParecerAndUsuariosParecer_id(ParecerStatus status, Long usuarioId);

}
