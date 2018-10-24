package com.agfgerador.compartilhado.service;

import java.util.List;


public interface SqlJdbcsService {
	
	public boolean callProcedure (String chamada,List<Object> parametros);
	
	public boolean executeSql(String sql);
	
}
