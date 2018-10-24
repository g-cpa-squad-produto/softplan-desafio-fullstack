package com.agfgerador.compartilhado.dao;

import java.sql.Connection;

public interface ConectBDDAO {

	Connection getConnection();	
	
	void close(Connection conn);
}
