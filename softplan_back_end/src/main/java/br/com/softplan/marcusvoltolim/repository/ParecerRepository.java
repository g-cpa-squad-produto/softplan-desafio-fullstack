package br.com.softplan.marcusvoltolim.repository;

import br.com.softplan.marcusvoltolim.model.Parecer;
import org.springframework.stereotype.Repository;

@Repository
public interface ParecerRepository extends EntityRepository<Parecer> {
	
	//	List<Parecer> findAllByUsuarioModificadorAndSituacao(Usuario responsavel, SituacaoParecer situacao);
	
	
}
