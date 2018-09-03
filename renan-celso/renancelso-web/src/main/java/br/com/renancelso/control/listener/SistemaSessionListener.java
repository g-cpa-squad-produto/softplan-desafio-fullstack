package br.com.renancelso.control.listener;

import javax.inject.Inject;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Renan Celso
 */
@WebListener
public class SistemaSessionListener implements HttpSessionListener {

	@Inject
	protected Logger log;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		PropertyConfigurator.configure(se.getSession().getServletContext().getRealPath("/") + "WEB-INF/log4j.properties");
		log.info("[Iniciando sessao " + se.getSession().getId() + "]");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.info("[Destruindo sessao " + se.getSession().getId() + "]");		
	}

}