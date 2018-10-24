 package com.agfgerador.gerenciadorprocessos.service;
 
 import java.util.List;
 import com.agfgerador.compartilhado.service.ServicePadrao;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;

 public interface ProcessoService extends ServicePadrao {

   public abstract Long getNumberRecordsFilter(ObjetoPadrao obj);
   public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
}