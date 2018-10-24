 package com.agfgerador.gerenciadorprocessos.dao;
 
 import java.util.List;
 import com.agfgerador.compartilhado.dao.DAOPadraoSemId;
 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;

   public interface ParecerDAO extends DAOPadraoSemId {

      public abstract List<ObjetoPadraoSemId> filter(ObjetoPadraoSemId obj, int pagesize, int page);
      public Long getNumberRecordsFilter(ObjetoPadraoSemId obj);

}