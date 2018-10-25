package br.com.softplan.processo.service;

import java.util.List;

import br.com.softplan.processo.modelos.Processo;

/**
 * @author emanuel
 *
 */
public interface ProcessoService {

	public Processo atualizarOuSalvar(Processo processo);

	public Processo excluir(Integer id);

	public List<Processo> listarPorUsuario(Integer idUsuario);

	public List<Processo> listarProcessoSemParecer(Integer idUsuario);

	public List<Processo> listarTodos();

}