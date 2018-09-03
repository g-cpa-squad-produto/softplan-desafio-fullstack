package br.com.renancelso.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import br.com.renancelso.padrao.GenericService;
import br.com.renancelso.service.model.Processo;
import br.com.renancelso.service.model.UsuarioProcesso;

/**
 * @author Renan Celso
 */
@Stateless
public class TriadorService extends GenericService implements TriadorServiceLocal {

	private static final long serialVersionUID = 8393847935595670179L;

	protected Logger log = Logger.getLogger(TriadorService.class.getName());

	@SuppressWarnings("unchecked")
	@Override
	public List<Processo> listarProcessos() {
		List<Processo> listaProcessos = new ArrayList<>();
		try {
			listaProcessos = (List<Processo>) consultarTodos(Processo.class);
			return listaProcessos;
		} catch (Exception e) {
			return listaProcessos;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioProcesso> buscarUsuariosVinculados(Processo processoSelecionado) {
		List<UsuarioProcesso> listaUsuarioProcesso = new ArrayList<>();
		try {
			listaUsuarioProcesso = (List<UsuarioProcesso>) consultarPorQuery(
					"select o from UsuarioProcesso o where o.processo.id = " + processoSelecionado.getId(), 0, 0);

			return listaUsuarioProcesso;
		} catch (Exception e) {
			return listaUsuarioProcesso;
		}
	}

}
