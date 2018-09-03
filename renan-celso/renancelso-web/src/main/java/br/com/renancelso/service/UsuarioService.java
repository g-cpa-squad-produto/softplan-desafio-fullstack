package br.com.renancelso.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import br.com.renancelso.padrao.GenericService;
import br.com.renancelso.service.model.Usuario;

/**
 * @author Renan Celso
 */
@Stateless
public class UsuarioService extends GenericService implements UsuarioServiceLocal {
	
	private static final long serialVersionUID = 926453571440089925L;

	protected Logger log = Logger.getLogger(UsuarioService.class.getName());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuarios() {
		List<Usuario> listaUsuarios = new ArrayList<>();		
		try {			
			listaUsuarios = (List<Usuario>) consultarTodos(Usuario.class);			
			return listaUsuarios;
		} catch (Exception e) {
			return listaUsuarios;
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuariosFinalizadores() {
		List<Usuario> listaUsuarios = new ArrayList<>();		
		try {			
			listaUsuarios = (List<Usuario>) 
					consultarPorQuery("select o from Usuario o where o.tipoUsuario = 'FINALIZADOR'", 0, 0);		
			return listaUsuarios;
		} catch (Exception e) {
			return listaUsuarios;
		}	
	}   	
	

}
