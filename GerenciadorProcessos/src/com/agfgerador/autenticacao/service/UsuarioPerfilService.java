package com.agfgerador.autenticacao.service;

import java.util.List;

import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.service.ServicePadrao;

public interface UsuarioPerfilService extends ServicePadrao {

	
	public List<UsuarioPerfil> findByNome(String nome);
	
	public List<UsuarioPerfil> findByUsuario(Usuario p,TipoSistema s);
	
	public List<UsuarioPerfil> findByUsuario(Usuario p);
	
	public boolean verificaPermissaoSistemaUsuario(Usuario p,TipoSistema sistema);
	
	public boolean haPerfilAdm(Long codusuario);
	
	public List<Usuario> loadAllExcptAdmin(TipoSistema sistema);
	
	public Long getNumberRecordsFilter(ObjetoPadrao obj);
	
	public  List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
}
