package com.agfgerador.autenticacao.service;

import java.sql.Connection;

public interface ReportService {

	public Connection getConnection();
	public void close(Connection conn);
}
