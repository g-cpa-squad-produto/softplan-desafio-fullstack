package br.com.renancelso.padrao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import br.com.renancelso.control.filter.CustomCharacterEncodingFilter;

/**
 * @author Renan Celso
 */
public class BaseControl implements Serializable{
	
	private static final long serialVersionUID = 6653407730404794541L;

	protected Logger log = Logger.getLogger(BaseControl.class.getName());
	
	@Resource(mappedName = "java:/jdbc/renancelsoDS")
	private transient DataSource dataSource;	

	public BaseControl() {
		Locale.setDefault(new Locale("pt", "BR"));	
	}

	public static void addErrorMessage(List<?> listaErro) {
		for (int i = 0; i < listaErro.size(); i++) {
			String msg = (String) listaErro.get(i);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, facesMsg);
		}
	}

	public static String addErrorMessage(String erro) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, erro, erro);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
		return null;
	}

	public static String addInfoMessage(List<?> listaMsg) {
		for (int i = 0; i < listaMsg.size(); i++) {
			String msg = (String) listaMsg.get(i);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage("Info", facesMsg);
		}
		return null;
	}

	public static String addInfoMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("Info", facesMsg);
		return null;
	}

	public static String addWarnMessage(List<?> listaMsg) {
		for (int i = 0; i < listaMsg.size(); i++) {
			String msg = (String) listaMsg.get(i);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage("Warn", facesMsg);
		}
		return null;
	}

	/**
	 * 
	 * @author Renan Celso
	 * 
	 * @param msg
	 * @return
	 */
	public static String addWarnMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("Warn", facesMsg);
		return null;
	}

	/**
	 * @author Renan Celso
	 * 
	 * @param listaMsg
	 * @return
	 */
	public static String addFatalMessage(List<?> listaMsg) {
		for (int i = 0; i < listaMsg.size(); i++) {
			String msg = (String) listaMsg.get(i);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage("Fatal", facesMsg);
		}
		return null;
	}

	/**
	 * 
	 * @author Renan Celso
	 * 
	 * @param msg
	 * @return
	 */
	public static String addFatalMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("Fatal", facesMsg);
		return null;
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 * 
	 * @author Renan Celso, em 30/10/2014
	 * 
	 *         Transforma um objeto do tipo File em byte[]
	 * 
	 */
	public byte[] fileToByte(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int bytesRead;
		while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}
		fis.close();
		return baos.toByteArray();
	}

	public boolean validarEmail(String email) {
		boolean isEmailIdValid = false;
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
			}
		}
		return isEmailIdValid;
	}

	public static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public void redirect(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/" + FacesContext.getCurrentInstance().getExternalContext().getContextName().toLowerCase() + page);
		} catch (IOException e) {
			log.error(e);	
		}
	}
	
}