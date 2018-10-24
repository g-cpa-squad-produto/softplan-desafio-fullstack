 package com.agfgerador.autenticacao.service;
 
 import java.util.List;
 import com.agfgerador.compartilhado.service.ServicePadrao;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;

 public interface IconesService extends ServicePadrao {

   public abstract Long getNumberRecordsFilter(ObjetoPadrao obj);
   public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
}