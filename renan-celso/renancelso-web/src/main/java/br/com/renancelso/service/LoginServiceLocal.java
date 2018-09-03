package br.com.renancelso.service;

import java.util.List;

import javax.ejb.Local;

import br.com.renancelso.padrao.GenericServiceInterface;
import br.com.renancelso.service.model.Usuario;

/**
 * @author Renan Celso
 */
@Local
public interface LoginServiceLocal extends GenericServiceInterface{

	public List<Usuario> buscarUsuarioPorLogin(String email);

}
