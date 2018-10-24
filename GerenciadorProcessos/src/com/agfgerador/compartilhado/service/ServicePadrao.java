package com.agfgerador.compartilhado.service;

import java.util.List;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;

public interface ServicePadrao {

	public void createNew(ObjetoPadrao obj);
	
	public void createNew(ObjetoPadraoSemId obj);

	public void update(ObjetoPadrao obj);

	public void remove(ObjetoPadrao obj);

	public ObjetoPadrao findById(Long id);

	public List<ObjetoPadrao> findAll();

	public List<ObjetoPadrao> findAll(int pagesize, int page);

	public Long getNumberRecords();

	public List<ObjetoPadrao> filter(ObjetoPadrao obj);

	List<ObjetoPadrao> findAll(String value,int pagesize, int page);

	Long getNumberRecords(String value);
	
	
	
	
}
