 package com.agfgerador.autenticacao.service;
 
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 import com.agfgerador.autenticacao.domain.Metodo;
 import com.agfgerador.autenticacao.domain.Modulo;
import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.service.LogService;
 import com.agfgerador.autenticacao.dao.PerfilDAO;


 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
   @Service("perfilService")
	  public class PerfilServiceImpl implements PerfilService {

     private PerfilDAO perfilDAO;

     @Autowired
     private LogService logService;
 
     @Autowired
     public PerfilServiceImpl(PerfilDAO perfilDAO) {
 	   this.perfilDAO = perfilDAO;
     }

     @Transactional
     public void createNew(ObjetoPadrao obj) {
       perfilDAO.persist(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.ADICIONAR);
     }

     @Transactional
     public void update(ObjetoPadrao obj) {
       perfilDAO.update(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.ATUALIZAR);
     }

     @Transactional
     public void remove(ObjetoPadrao obj) {
       perfilDAO.delete(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.REMOVER);
     }

     @Transactional(readOnly=true)
     public ObjetoPadrao findById(Long id) {
       return perfilDAO.loadById(id);
     }

     @Override
     public List<ObjetoPadrao> findAll() {
       return perfilDAO.findAll();
     }

     @Override
     public List<ObjetoPadrao> findAll(int pagesize, int page) {
        return perfilDAO.findAll( pagesize,  page);
     }

     @Override
     public Long getNumberRecords() {
       return perfilDAO.getNumberRecords();     }

     @Transactional(readOnly=true)
     public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
       return perfilDAO.filter(obj);
     }

     @Override
     public List<ObjetoPadrao> findAll(String value, int pagesize, int page) {
       return null;
     }

     @Override
     public Long getNumberRecords(String value) {
       return null;
     }

     @Override
     public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page){
       return perfilDAO.filter(obj, pagesize, page);
     }

     @Override
     public Long getNumberRecordsFilter(ObjetoPadrao obj){
       return perfilDAO.getNumberRecordsFilter(obj);
     }

     @Override
     public void createNew(ObjetoPadraoSemId obj) {

     }
     
 	public List<Perfil> findByNome(String nome){
 		return null;
 	}
	
 	public List<Perfil> findBySistema(TipoSistema sis){
 		return null;
 	}
 	
 	public List<ObjetoPadrao> findByComum(){
 		return null;
 	}

}
