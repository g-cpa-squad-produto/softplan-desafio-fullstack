package com.agfgerador.compartilhado.dao;

import java.util.List;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;

public interface DAOPadrao {

	ObjetoPadrao loadById(Long id);
	void persist(ObjetoPadrao obj);
	void update(ObjetoPadrao obj);
	void delete(ObjetoPadrao obj);
	List<ObjetoPadrao> findAll();
	List<ObjetoPadrao> findAll(int pagesize, int page);
	Long getNumberRecords();
	List<ObjetoPadrao> filter(ObjetoPadrao obj);
	List<ObjetoPadrao> findAll(String value,int pagesize, int page);
	Long getNumberRecords(String value);
	

}
