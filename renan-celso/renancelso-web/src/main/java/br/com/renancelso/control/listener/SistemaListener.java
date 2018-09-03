package br.com.renancelso.control.listener;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

/** 
 * @author Renan Celso 
 * Listener para Controle de sessão da aplicação. 
 */
@WebListener
public class SistemaListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	public static final String LOGIN_PAGE = "/";

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		
		String currentPage = facesContext.getExternalContext().getRequestServletPath();
		boolean paginaPublica = (currentPage.lastIndexOf("publico/") > -1);
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

		Object usuarioLogado = null;			

		if (session != null) {
			if(session.getAttribute("usuarioLogado") != null) {
				usuarioLogado = session.getAttribute("usuarioLogado");				
			}
		}

		if (!paginaPublica && usuarioLogado == null) {			
			redirect(LOGIN_PAGE);			
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
	private void redirect(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath().toLowerCase() + page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}