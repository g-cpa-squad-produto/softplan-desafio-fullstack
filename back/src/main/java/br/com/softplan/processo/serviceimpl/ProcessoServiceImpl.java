package br.com.softplan.processo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.processo.modelos.Processo;
import br.com.softplan.processo.repository.ProcessoRepository;
import br.com.softplan.processo.service.ProcessoService;

/**
 * @author emanuel
 *
 */
@Service
public class ProcessoServiceImpl implements ProcessoService {

	@Autowired
	private ProcessoRepository repository;

	@Override
	public Processo atualizarOuSalvar(Processo processo) {
		Processo processoSalvo = repository.save(processo);
		return processoSalvo;
	}

	@Override
	public Processo excluir(Integer id) {
		// Apenas seta o processo como deletado
		Processo processo = repository.getOne(id);
		processo.setFinalizado(Boolean.FALSE);
		processo.setDeletado(Boolean.TRUE);
		repository.save(processo);
		return processo;
	}

	@Override
	public List<Processo> listarPorUsuario(Integer idUsuario) {
		List<Processo> processos = repository.listarProcessosDeUmUsuario(idUsuario);
		return processos;
	}

	@Override
	public List<Processo> listarTodos() {
		List<Processo> processos = repository.listarTodosProcessos();
		return processos;
	}

	@Override
	public List<Processo> listarProcessoSemParecer(Integer idUsuario) {
		List<Processo> processos = repository.listarProcessosSemParecer(idUsuario);
		return processos;
	}

}
