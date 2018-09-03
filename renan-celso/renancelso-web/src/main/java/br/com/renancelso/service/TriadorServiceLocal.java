package br.com.renancelso.service;

import java.util.List;

import javax.ejb.Local;

import br.com.renancelso.padrao.GenericServiceInterface;
import br.com.renancelso.service.model.Processo;
import br.com.renancelso.service.model.UsuarioProcesso;

/**
 * @author Renan Celso
 */
@Local
public interface TriadorServiceLocal extends GenericServiceInterface{

	public List<Processo> listarProcessos();

	public List<UsuarioProcesso> buscarUsuariosVinculados(Processo processoSelecionado);

}
