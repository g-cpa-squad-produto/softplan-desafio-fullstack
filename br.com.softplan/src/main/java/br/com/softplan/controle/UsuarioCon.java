package br.com.softplan.controle;

import java.util.List;

import br.com.softplan.dao.UsuarioDao;
import br.com.softplan.modelo.Usuario;

/**
 * @since 13/08/2018 21:18
 * @author Fernando.Rauber
 */
public class UsuarioCon {

	private UsuarioDao dao;
	private Usuario usuario;
	
    public UsuarioCon() {
    	dao = new UsuarioDao();
    	usuario = new Usuario();
    }
    
    public void salvar() {
    	popular();
	    dao.salvar(usuario);
    }
    
    private void popular() {
    	usuario.setNome("Maiara");
	    usuario.setIdade(26);
    }
    
    public List<Usuario> listar() {
    	return dao.listar();
    }
    
    
}
