package br.com.softplan.processo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.processo.modelos.Processo;
import br.com.softplan.processo.repository.ProcessoRepository;
import br.com.softplan.processo.service.ProcessoService;

@Service
public class ProcessoServiceImpl implements ProcessoService {

	@Autowired
	private ProcessoRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.softplan.processo.serviceimpl.ProcessoService#atualizarOuSalvar(br.com
	 * .softplan.processo.modelo.Processo)
	 */
	@Override
	public Processo atualizarOuSalvar(Processo processo) {
		Processo processoSalvo = repository.save(processo);
		return processoSalvo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softplan.processo.serviceimpl.ProcessoService#excluir(java.lang.
	 * Integer)
	 */
	@Override
	public Processo excluir(Integer id) {
		Processo processo = repository.getOne(id);
		processo.setAtivo(Boolean.FALSE);
		processo.setDeletado(Boolean.TRUE);
		repository.save(processo);
		return processo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.softplan.processo.serviceimpl.ProcessoService#listarPorUsuario(java.
	 * lang.Integer)
	 */
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
	public List<Processo> listarProcessoSemParecer() {
		List<Processo> processos = repository.listarProcessosSemParecer();
		return processos;
	}

}
