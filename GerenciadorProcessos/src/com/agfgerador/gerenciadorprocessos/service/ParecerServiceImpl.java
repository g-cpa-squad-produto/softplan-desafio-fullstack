 package com.agfgerador.gerenciadorprocessos.service;
 
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 import com.agfgerador.autenticacao.domain.Metodo;
 import com.agfgerador.autenticacao.domain.Modulo;
 import com.agfgerador.autenticacao.service.LogService;
 import com.agfgerador.gerenciadorprocessos.dao.ParecerDAO;


 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
   @Service("parecerService")
	  public class ParecerServiceImpl implements ParecerService {

     private ParecerDAO parecerDAO;

     @Autowired
     private LogService logService;
 
     @Autowired
     public ParecerServiceImpl(ParecerDAO parecerDAO) {
 	   this.parecerDAO = parecerDAO;
     }

     @Transactional
     public void createNew(ObjetoPadraoSemId obj) {
       parecerDAO.persist(obj);
       logService.createNewSemId(obj, Modulo.PROJETOBASE, Metodo.ADICIONAR);
     }

     @Transactional
     public void update(ObjetoPadraoSemId obj) {
       parecerDAO.update(obj);
       logService.createNewSemId(obj, Modulo.PROJETOBASE, Metodo.ATUALIZAR);
     }

     @Transactional
     public void remove(ObjetoPadraoSemId obj) {
       parecerDAO.delete(obj);
       logService.createNewSemId(obj, Modulo.PROJETOBASE, Metodo.REMOVER);
     }

     @Transactional(readOnly=true)
     public ObjetoPadraoSemId findById(Long id) {
       return parecerDAO.loadById(id);
     }

     @Override
     public List<ObjetoPadraoSemId> findAll() {
       return parecerDAO.findAll();
     }

     @Override
     public List<ObjetoPadraoSemId> findAll(int pagesize, int page) {
        return parecerDAO.findAll( pagesize,  page);
     }

     @Override
     public Long getNumberRecords() {
       return parecerDAO.getNumberRecords();     }

     @Transactional(readOnly=true)
     public List<ObjetoPadraoSemId> filter(ObjetoPadraoSemId obj) {
       return parecerDAO.filter(obj);
     }

     @Override
     public List<ObjetoPadraoSemId> findAll(String value, int pagesize, int page) {
       return null;
     }

     @Override
     public Long getNumberRecords(String value) {
       return null;
     }

     @Override
     public List<ObjetoPadraoSemId> filter(ObjetoPadraoSemId obj, int pagesize, int page){
       return parecerDAO.filter(obj, pagesize, page);
     }

     @Override
     public Long getNumberRecordsFilter(ObjetoPadraoSemId obj){
       return parecerDAO.getNumberRecordsFilter(obj);
     }

     @Override
     public void createNewSemId(ObjetoPadraoSemId obj) {
       parecerDAO.persist(obj);
       logService.createNewSemId(obj, Modulo.PROJETOBASE, Metodo.ADICIONAR);
     }

}
