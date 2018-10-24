package com.agfgerador.compartilhado.service;

import java.sql.Connection;

public interface ConectBDService {

	public Connection getConnection();
	public void close(Connection conn);
}
