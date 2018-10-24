 package com.agfgerador.autenticacao.service;
 
 import java.util.List;
 import com.agfgerador.compartilhado.service.ServicePadrao;
import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

 public interface PerfilService extends ServicePadrao {

   public abstract Long getNumberRecordsFilter(ObjetoPadrao obj);
   public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
   
	public List<Perfil> findByNome(String nome);
	
	public List<Perfil> findBySistema(TipoSistema sis);
	
	public List<ObjetoPadrao> findByComum();
}