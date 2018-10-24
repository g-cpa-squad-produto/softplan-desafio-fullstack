 package com.agfgerador.gerenciadorprocessos.service;
 
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 import com.agfgerador.autenticacao.domain.Metodo;
 import com.agfgerador.autenticacao.domain.Modulo;
 import com.agfgerador.autenticacao.service.LogService;
 import com.agfgerador.gerenciadorprocessos.dao.TipopessoaDAO;


 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
   @Service("tipopessoaService")
	  public class TipopessoaServiceImpl implements TipopessoaService {

     private TipopessoaDAO tipopessoaDAO;

     @Autowired
     private LogService logService;
 
     @Autowired
     public TipopessoaServiceImpl(TipopessoaDAO tipopessoaDAO) {
 	   this.tipopessoaDAO = tipopessoaDAO;
     }

     @Transactional
     public void createNew(ObjetoPadrao obj) {
       tipopessoaDAO.persist(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.ADICIONAR);
     }

     @Transactional
     public void update(ObjetoPadrao obj) {
       tipopessoaDAO.update(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.ATUALIZAR);
     }

     @Transactional
     public void remove(ObjetoPadrao obj) {
       tipopessoaDAO.delete(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.REMOVER);
     }

     @Transactional(readOnly=true)
     public ObjetoPadrao findById(Long id) {
       return tipopessoaDAO.loadById(id);
     }

     @Override
     public List<ObjetoPadrao> findAll() {
       return tipopessoaDAO.findAll();
     }

     @Override
     public List<ObjetoPadrao> findAll(int pagesize, int page) {
        return tipopessoaDAO.findAll( pagesize,  page);
     }

     @Override
     public Long getNumberRecords() {
       return tipopessoaDAO.getNumberRecords();     }

     @Transactional(readOnly=true)
     public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
       return tipopessoaDAO.filter(obj);
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
       return tipopessoaDAO.filter(obj, pagesize, page);
     }

     @Override
     public Long getNumberRecordsFilter(ObjetoPadrao obj){
       return tipopessoaDAO.getNumberRecordsFilter(obj);
     }

     @Override
     public void createNew(ObjetoPadraoSemId obj) {

     }

}
