package com.agfgerador.autenticacao.service;

import java.util.List;

import com.agfgerador.autenticacao.domain.TipoUsuario;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.service.ServicePadrao;
 
public interface UsuarioService extends ServicePadrao {

	public void updateSemLog(ObjetoPadrao obj);
	public List<Usuario> findByNome(String nome);
	public Usuario findByLogin(String login);
	public Usuario findByAutentication(String login,String senha);
	public List<String> getUserOnLine();
	public  List<Usuario> findGestorControla();
	public  List<Usuario> findAnalistaControla();
	public List<Usuario> filter(Usuario obj, boolean allHabilitado,TipoUsuario tipo);
	public Long avisos(boolean temAvisos);
	public abstract Long getNumberRecordsFilter(ObjetoPadrao obj);
	public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
	
}
	
