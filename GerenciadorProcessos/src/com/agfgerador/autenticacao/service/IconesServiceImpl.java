 package com.agfgerador.autenticacao.service;
 
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 import com.agfgerador.autenticacao.domain.Metodo;
 import com.agfgerador.autenticacao.domain.Modulo;
 import com.agfgerador.autenticacao.service.LogService;
 import com.agfgerador.autenticacao.dao.IconesDAO;


 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
   @Service("iconesService")
	  public class IconesServiceImpl implements IconesService {

     private IconesDAO iconesDAO;

     @Autowired
     private LogService logService;
 
     @Autowired
     public IconesServiceImpl(IconesDAO iconesDAO) {
 	   this.iconesDAO = iconesDAO;
     }

     @Transactional
     public void createNew(ObjetoPadrao obj) {
       iconesDAO.persist(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.ADICIONAR);
     }

     @Transactional
     public void update(ObjetoPadrao obj) {
       iconesDAO.update(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.ATUALIZAR);
     }

     @Transactional
     public void remove(ObjetoPadrao obj) {
       iconesDAO.delete(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.REMOVER);
     }

     @Transactional(readOnly=true)
     public ObjetoPadrao findById(Long id) {
       return iconesDAO.loadById(id);
     }

     @Override
     public List<ObjetoPadrao> findAll() {
       return iconesDAO.findAll();
     }

     @Override
     public List<ObjetoPadrao> findAll(int pagesize, int page) {
        return iconesDAO.findAll( pagesize,  page);
     }

     @Override
     public Long getNumberRecords() {
       return iconesDAO.getNumberRecords();     }

     @Transactional(readOnly=true)
     public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
       return iconesDAO.filter(obj);
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
       return iconesDAO.filter(obj, pagesize, page);
     }

     @Override
     public Long getNumberRecordsFilter(ObjetoPadrao obj){
       return iconesDAO.getNumberRecordsFilter(obj);
     }

     @Override
     public void createNew(ObjetoPadraoSemId obj) {

     }

}
