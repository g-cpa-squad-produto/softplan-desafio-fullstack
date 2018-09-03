package br.com.renancelso.padrao;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Renan Celso
 *
 */
public class Contexto implements Serializable{

    private static final long serialVersionUID = -4139187284146344790L;

    
    private Contexto(){
    	
    }
    
	public static HttpSession getSessao() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public static ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public static String getRealPath(String caminho) {
        ServletContext request = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
       return request.getRealPath(caminho);
    }

}
