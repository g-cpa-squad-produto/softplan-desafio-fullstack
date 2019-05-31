package br.com.softplan.marcusvoltolim.service;

import br.com.softplan.marcusvoltolim.model.Parecer;
import br.com.softplan.marcusvoltolim.repository.ParecerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParecerService extends AbstractService<Parecer> {
	
	private final ParecerRepository repository;
	
	@Autowired
	public ParecerService(ParecerRepository repository) {
		super(repository, Parecer.class);
		this.repository = repository;
	}
	
	//	public List<Parecer> findAllPendentesPorUsuario(Usuario responsavel) {
	//		return repository.findAllByUsuarioModificadorAndSituacao(responsavel, SituacaoParecer.PENDENTE);
	//	}
	//
	//	public Parecer getUltimoProduto() {
	//		return repository.findFirstByOrderByIdDesc();
	//	}
	
	@Override
	public List<Parecer> findAllBy(String filtro) {
		return null;
	}
	
	@Override
	void updateAtributos(Parecer entidadePraAtualizar, String dadosAtualizadosJson) {
		Parecer prodAlterado = entityFromJson(dadosAtualizadosJson);
		entidadePraAtualizar.setConclusao(prodAlterado.getConclusao());
		entidadePraAtualizar.setDescricao(prodAlterado.getDescricao());
	}
	
}
