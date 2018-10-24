 package com.agfgerador.gerenciadorprocessos.service;
 
 import java.util.List;
 import com.agfgerador.compartilhado.service.ServicePadraoSemId;
 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;

 public interface ParecerService extends ServicePadraoSemId {

   public abstract Long getNumberRecordsFilter(ObjetoPadraoSemId obj);
   public abstract List<ObjetoPadraoSemId> filter(ObjetoPadraoSemId obj, int pagesize, int page);
}