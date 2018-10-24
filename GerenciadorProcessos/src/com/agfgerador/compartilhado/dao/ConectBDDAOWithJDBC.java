package com.agfgerador.compartilhado.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConectBDDAOWithJDBC implements ConectBDDAO {

	private Properties props; 
	private String dataBase;
	
	
	
	public ConectBDDAOWithJDBC(String dataBase) {
		this.dataBase = dataBase;
	}

	public Connection getConnection(){
		props  = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/application.properties"); 

		try{  
			props.load(in);  
			in.close();  
		}  
		catch(IOException e){
			e.printStackTrace();
		}     

		try {
			
			Class.forName((String)props.getProperty(dataBase+".driver"));
			
			return DriverManager.getConnection((String)props.getProperty(dataBase+".url"), 
					(String)props.getProperty(dataBase+".username"), (String)props.getProperty(dataBase+".password"));
			
		} catch (Exception e) {
			
			System.out.println("erro na conexão do relatório"+e.getMessage( ));
		}
		return null;
	}

	public void close(Connection conn) {
		try {
	
			if (conn != null)
				conn.close( );
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}


}