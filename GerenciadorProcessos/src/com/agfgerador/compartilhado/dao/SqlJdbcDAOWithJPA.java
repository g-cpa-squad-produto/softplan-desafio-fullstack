package com.agfgerador.compartilhado.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class SqlJdbcDAOWithJPA implements SqlJdbcDAO {


	public boolean callProcedure(String chamada, List<Object> parametros)
	{   boolean ret = false ;
		ConectBDDAO conexao = new ConectBDDAOWithJDBC("database");
	    Connection conn;
		
		try {
			conn =  conexao.getConnection();
			CallableStatement cs = conn.prepareCall(chamada);
			int i;
			if(parametros!=null)
			{	for (i=0;i<parametros.size();i++)
				{	
					cs = setParametros(parametros.get(i), cs, i);
				}
				
			}
			
			cs.execute();
			cs.close();
			conn.close();
			ret = true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	    
	}
	
	public CallableStatement  setParametros(Object obj,CallableStatement cs,int indice)
	{	try
		{	if (obj instanceof Double)
			{
				cs.setDouble(indice+1, (double) obj);
			}
			else if (obj instanceof Long)
			{
				cs.setLong(indice+1, (long) obj);
			}
			else if (obj instanceof Integer)
			{
				cs.setInt(indice+1, (Integer) obj);
			}
			else if (obj instanceof Date)
			{
				cs.setDate(indice+1,  (java.sql.Date) obj);
			}
			else if (obj instanceof Boolean)
			{
				cs.setBoolean(indice+1,  (Boolean) obj);
			}
			else 
			{
				cs.setString(indice+1, (String) obj);
			}
		}
		catch(Exception e)
		{
			System.out.println("erro no setParametros "+e);
		}
	
		return cs;
	}


	public boolean executeSql(String sql) {
		 boolean ret = false ;
		 ConectBDDAO conexao = new ConectBDDAOWithJDBC("database");
		 Connection conn;
		 try {
				conn =  conexao.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				
				preparedStatement.executeUpdate();
				preparedStatement.close();
				conn.close();
				ret = true;
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
		 return ret;
	}
	
	
}