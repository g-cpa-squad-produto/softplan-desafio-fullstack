package br.com.softplan.marcusvoltolim.service;

import br.com.softplan.marcusvoltolim.model.Parecer;
import br.com.softplan.marcusvoltolim.model.Processo;
import br.com.softplan.marcusvoltolim.repository.ParecerRepository;
import br.com.softplan.marcusvoltolim.repository.ProcessoRepository;
import br.com.softplan.marcusvoltolim.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ProcessoService extends AbstractService<Processo> {
	
	@SuppressWarnings({"unused", "FieldCanBeLocal"})
	private final ProcessoRepository repository;
	private final ParecerRepository parecerRepository;
	private final ParecerService parecerService;
	
	@Autowired
	public ProcessoService(ProcessoRepository repository, ParecerRepository parecerRepository,
	                       ParecerService parecerService) {
		super(repository, Processo.class);
		this.repository = repository;
		this.parecerRepository = parecerRepository;
		this.parecerService = parecerService;
	}
	
	@Override
	public List<Processo> findAllBy(String filtro) {
		return null;
	}
	
	@Override
	void updateAtributos(Processo processoPraAtualizar, String dadosAtualizadosJson) {
		Processo processoAlterado = entityFromJson(dadosAtualizadosJson);
		processoPraAtualizar.setTitulo(processoAlterado.getTitulo());
		processoPraAtualizar.setDescricao(processoAlterado.getDescricao());
		adicionarPareceres(processoPraAtualizar, dadosAtualizadosJson);
	}
	
	private void adicionarPareceres(Processo processo, String dadosAtualizadosJson) {
		Map dados = JsonUtils.fromJson(dadosAtualizadosJson, Map.class);
		
		if (dados.containsKey("finalizadoresEscolhidos")) {
			List<String> finalizadores = (List<String>) dados.get("finalizadoresEscolhidos");
			finalizadores.forEach(finalizador -> {
				Parecer parecer = parecerRepository.save(new Parecer(finalizador, processo));
				processo.getPareceres().add(parecer);
			});
		}
	}
	
}
