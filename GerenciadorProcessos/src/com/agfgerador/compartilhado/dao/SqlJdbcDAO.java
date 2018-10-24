package com.agfgerador.compartilhado.dao;

import java.util.List;


public interface SqlJdbcDAO {

	boolean callProcedure (String chamada,List<Object> parametros);
	boolean executeSql (String sql);
	
	
}
