package com.agfgerador.autenticacao.dao;

import java.util.List;

import com.agfgerador.autenticacao.domain.TipoUsuario;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.compartilhado.dao.DAOPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

public interface UsuarioDAO extends DAOPadrao {
	
	List<Usuario> findByNome(String nome);
	List<Usuario> filter(Usuario obj, boolean allHabilitado,TipoUsuario tipo);
	Usuario findByLogin(String login);
	Usuario findByAutentication(String login,String senha);
	List<Usuario> findGestorControla();	
	List<Usuario> findAnalistaControla();
	Long avisos(boolean temAvisos);
	
	public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
	public Long getNumberRecordsFilter(ObjetoPadrao obj);
}
