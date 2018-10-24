package com.agfgerador.compartilhado.service;

import java.util.List;

import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;

public interface ServicePadraoSemId {

	public void createNewSemId(ObjetoPadraoSemId obj);

	public void update(ObjetoPadraoSemId obj);

	public void remove(ObjetoPadraoSemId obj);

	public ObjetoPadraoSemId findById(Long id);

	public List<ObjetoPadraoSemId> findAll();
	
	public List<ObjetoPadraoSemId> findAll(int pagesize, int page);
	
	public Long getNumberRecords();
	
	public List<ObjetoPadraoSemId> filter(ObjetoPadraoSemId obj);
	
	List<ObjetoPadraoSemId> findAll(String value,int pagesize, int page);
	
	Long getNumberRecords(String value);
	
	
	
	
}
