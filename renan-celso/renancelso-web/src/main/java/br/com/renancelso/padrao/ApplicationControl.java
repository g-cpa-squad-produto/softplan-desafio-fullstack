package br.com.renancelso.padrao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/** 
 * @author Renan Celso, em 23/09/2015	
 */
@ManagedBean(name = "applicationControl")
@ApplicationScoped
public class ApplicationControl {
	
	@Inject
	protected Logger log;			
	private Date dataHoraInicio;
	
	@PostConstruct
	public void init() {	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);		
		PropertyConfigurator.configure(session.getServletContext().getRealPath("/") + "WEB-INF/log4j.properties");
		Locale.setDefault(new Locale("pt", "BR"));		
		dataHoraInicio = new Date();		
	}	
	
	public String getIniciarAplicacao(){
		
		return "";
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	
	public String getDataHoraInicioStr() {
		String formato = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        return formatter.format(dataHoraInicio);
	}
	
	
	
}
