package com.agfgerador.compartilhado.util;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;

public class Logger {
	
	private org.apache.log4j.Logger log; 

	public Logger(Class<?> clase)
	{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("../../log4j.properties");
		PropertyConfigurator.configure(url);
		log = org.apache.log4j.Logger.getLogger(clase.getName());
	}

	public void debug(String mensagem)
	{
		log.debug(new Exception().getStackTrace()[1].getMethodName()+" - "+mensagem);
	}

	public void info(String mensagem)
	{
		log.info(new Exception().getStackTrace()[1].getMethodName()+" - "+mensagem);
	}

	public void warn(String mensagem)
	{
		log.warn(new Exception().getStackTrace()[1].getMethodName()+" - "+mensagem);
	}

	public void error(String mensagem)
	{
		log.error(new Exception().getStackTrace()[1].getMethodName()+" - "+mensagem);
	}

	public void fatal(String mensagem)
	{
		log.fatal(new Exception().getStackTrace()[1].getMethodName()+" - "+mensagem);
	}
}
