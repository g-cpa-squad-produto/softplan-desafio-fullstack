 package com.agfgerador.gerenciadorprocessos.dao;
 
 import java.util.List;
 import com.agfgerador.compartilhado.dao.DAOPadrao;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;

   public interface ProcessoDAO extends DAOPadrao {

      public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
      public Long getNumberRecordsFilter(ObjetoPadrao obj);

}