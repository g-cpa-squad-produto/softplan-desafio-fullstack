package br.com.sofplan.processos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sofplan.processos.enums.Situacao;
import br.com.sofplan.processos.models.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

	@Query("select processo from Processo processo "
			+ "join processo.responsaveis responsaveis "
			+ "left join processo.parecer parecer "
			+ "where "
			+ "(parecer.id is null or parecer.situacao = :situacao) and "
			+ "responsaveis.id.responsavel.id = :usuarioId "
			+ "order by processo.id asc")
	List<Processo> findBySituacaoAndResponsavel(Situacao situacao, Long usuarioId);
	
}
