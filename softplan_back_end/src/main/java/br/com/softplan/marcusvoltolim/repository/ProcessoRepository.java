package br.com.softplan.marcusvoltolim.repository;

import br.com.softplan.marcusvoltolim.model.Processo;

public interface ProcessoRepository extends EntityRepository<Processo> {
	
	//	@Query("select Processo o")
	//	List<Processo> findAllByUsuarioAndBySituacao(String login, SituacaoParecer situacao);
	
}
