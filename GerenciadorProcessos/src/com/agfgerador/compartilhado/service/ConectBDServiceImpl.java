package com.agfgerador.compartilhado.service;

import java.sql.Connection;

import org.springframework.stereotype.Service;

import com.agfgerador.compartilhado.dao.ConectBDDAO;
import com.agfgerador.compartilhado.dao.ConectBDDAOWithJDBC;


@Service("conectBDService")
public class ConectBDServiceImpl implements ConectBDService{

	private ConectBDDAO reportDAO;
	
	public ConectBDServiceImpl() {
		this.reportDAO = new ConectBDDAOWithJDBC("database");
	}
	
	public Connection getConnection() {
		return reportDAO.getConnection();
	}

	public void close(Connection conn) {
		reportDAO.close(conn);
	}
	

}