package com.agfgerador.autenticacao.dao;

import java.util.List;

import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.compartilhado.dao.DAOPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

public interface UsuarioPerfilDAO extends DAOPadrao {

	List<UsuarioPerfil> findByNome(String nome);
	
	List<UsuarioPerfil> findByUsuario(Usuario p,TipoSistema s);
	
	List<UsuarioPerfil> findByUsuario(Usuario p);
	
	boolean verificaPermissaoSistemaUsuario(Usuario p,TipoSistema sistema);
	
	boolean haPerfilAdm(Long codusuario);
	
	List<Usuario> loadAllExcptAdmin(TipoSistema sistema);
	
	public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
    public Long getNumberRecordsFilter(ObjetoPadrao obj);
}
