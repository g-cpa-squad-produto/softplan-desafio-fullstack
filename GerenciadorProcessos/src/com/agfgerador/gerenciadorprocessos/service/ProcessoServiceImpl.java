 package com.agfgerador.gerenciadorprocessos.service;
 
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 import com.agfgerador.autenticacao.domain.Metodo;
 import com.agfgerador.autenticacao.domain.Modulo;
 import com.agfgerador.autenticacao.service.LogService;
 import com.agfgerador.gerenciadorprocessos.dao.ProcessoDAO;


 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
   @Service("processoService")
	  public class ProcessoServiceImpl implements ProcessoService {

     private ProcessoDAO processoDAO;

     @Autowired
     private LogService logService;
 
     @Autowired
     public ProcessoServiceImpl(ProcessoDAO processoDAO) {
 	   this.processoDAO = processoDAO;
     }

     @Transactional
     public void createNew(ObjetoPadrao obj) {
       processoDAO.persist(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.ADICIONAR);
     }

     @Transactional
     public void update(ObjetoPadrao obj) {
       processoDAO.update(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.ATUALIZAR);
     }

     @Transactional
     public void remove(ObjetoPadrao obj) {
       processoDAO.delete(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.REMOVER);
     }

     @Transactional(readOnly=true)
     public ObjetoPadrao findById(Long id) {
       return processoDAO.loadById(id);
     }

     @Override
     public List<ObjetoPadrao> findAll() {
       return processoDAO.findAll();
     }

     @Override
     public List<ObjetoPadrao> findAll(int pagesize, int page) {
        return processoDAO.findAll( pagesize,  page);
     }

     @Override
     public Long getNumberRecords() {
       return processoDAO.getNumberRecords();     }

     @Transactional(readOnly=true)
     public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
       return processoDAO.filter(obj);
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
       return processoDAO.filter(obj, pagesize, page);
     }

     @Override
     public Long getNumberRecordsFilter(ObjetoPadrao obj){
       return processoDAO.getNumberRecordsFilter(obj);
     }

     @Override
     public void createNew(ObjetoPadraoSemId obj) {

     }

}
