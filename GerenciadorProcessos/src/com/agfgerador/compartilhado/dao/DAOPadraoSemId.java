package com.agfgerador.compartilhado.dao;

import java.util.List;

import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;

public interface DAOPadraoSemId {
	
	ObjetoPadraoSemId loadById(Long id);
	
	void persist(ObjetoPadraoSemId obj);
	
	void update(ObjetoPadraoSemId obj);
	
	void delete(ObjetoPadraoSemId obj);
	
	List<ObjetoPadraoSemId> findAll();
	
	List<ObjetoPadraoSemId> findAll(int pagesize, int page);
	
	Long getNumberRecords();
	
	List<ObjetoPadraoSemId> filter(ObjetoPadraoSemId obj);
	
	List<ObjetoPadraoSemId> findAll(String value,int pagesize, int page);
	
	Long getNumberRecords(String value);
	

}
