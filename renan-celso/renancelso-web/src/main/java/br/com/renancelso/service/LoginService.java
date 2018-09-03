package br.com.renancelso.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.renancelso.padrao.GenericService;
import br.com.renancelso.service.model.Usuario;

/**
 * @author Renan Celso
 */
@Stateless
public class LoginService extends GenericService implements LoginServiceLocal {

    private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("unchecked")
	@Override
    public List<Usuario> buscarUsuarioPorLogin(String login) {		
    	try {
    		List<Usuario> listaUsuarios = new ArrayList<>();    		
    		StringBuilder sql = new StringBuilder();
    		sql.append("select o from ").append(Usuario.class.getSimpleName()).append(" o ");
    		sql.append(" where o.login = '").append(login).append("'");    		    		
    		listaUsuarios = (List<Usuario>) consultarPorQuery(sql.toString(), 0, 0);
	    	return listaUsuarios; 	 
    	} catch(Exception e) {
    		log.error(e);
    		return null;
    	}
    }	

}
