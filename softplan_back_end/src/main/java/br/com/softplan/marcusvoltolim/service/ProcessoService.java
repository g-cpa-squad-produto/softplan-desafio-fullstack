package br.com.softplan.marcusvoltolim.service;

import br.com.softplan.marcusvoltolim.model.Processo;
import br.com.softplan.marcusvoltolim.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProcessoService extends AbstractService<Processo> {
	
	@SuppressWarnings({"unused", "FieldCanBeLocal"})
	private final ProcessoRepository repository;
	private final ParecerService parecerService;
	
	@Autowired
	public ProcessoService(ProcessoRepository repository, ParecerService parecerService) {
		super(repository, Processo.class);
		this.repository = repository;
		this.parecerService = parecerService;
	}
	
	@Override
	public List<Processo> findAllBy(String filtro) {
		return null;
	}
	
	@Override
	void updateAtributos(Processo entidadePraAtualizar, String dadosAtualizadosJson) {
		Processo processoAlterado = entityFromJson(dadosAtualizadosJson);
		entidadePraAtualizar.setTitulo(processoAlterado.getTitulo());
		entidadePraAtualizar.setDescricao(processoAlterado.getDescricao());
	}
	
	//	public List<Processo> findAllPendentesPorUsuario(String login, String situacao) {
	////		return repository.findAllByUsuarioAndBySituacao(login, SituacaoParecer.valueOf(situacao));
	//	}
}
