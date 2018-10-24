 package com.agfgerador.gerenciadorprocessos.service;
 
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 import com.agfgerador.autenticacao.domain.Metodo;
 import com.agfgerador.autenticacao.domain.Modulo;
 import com.agfgerador.autenticacao.service.LogService;
 import com.agfgerador.gerenciadorprocessos.dao.PessoaDAO;


 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
   @Service("pessoaService")
	  public class PessoaServiceImpl implements PessoaService {

     private PessoaDAO pessoaDAO;

     @Autowired
     private LogService logService;
 
     @Autowired
     public PessoaServiceImpl(PessoaDAO pessoaDAO) {
 	   this.pessoaDAO = pessoaDAO;
     }

     @Transactional
     public void createNew(ObjetoPadrao obj) {
       pessoaDAO.persist(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.ADICIONAR);
     }

     @Transactional
     public void update(ObjetoPadrao obj) {
       pessoaDAO.update(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.ATUALIZAR);
     }

     @Transactional
     public void remove(ObjetoPadrao obj) {
       pessoaDAO.delete(obj);
       logService.createNew(obj, Modulo.PROJETOBASE, Metodo.REMOVER);
     }

     @Transactional(readOnly=true)
     public ObjetoPadrao findById(Long id) {
       return pessoaDAO.loadById(id);
     }

     @Override
     public List<ObjetoPadrao> findAll() {
       return pessoaDAO.findAll();
     }

     @Override
     public List<ObjetoPadrao> findAll(int pagesize, int page) {
        return pessoaDAO.findAll( pagesize,  page);
     }

     @Override
     public Long getNumberRecords() {
       return pessoaDAO.getNumberRecords();     }

     @Transactional(readOnly=true)
     public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
       return pessoaDAO.filter(obj);
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
       return pessoaDAO.filter(obj, pagesize, page);
     }

     @Override
     public Long getNumberRecordsFilter(ObjetoPadrao obj){
       return pessoaDAO.getNumberRecordsFilter(obj);
     }

     @Override
     public void createNew(ObjetoPadraoSemId obj) {

     }

}
