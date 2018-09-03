package br.com.renancelso.padrao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author Renan Celso
 */
public class DateFormat implements Serializable{

	private static final long serialVersionUID = 4751740972122623721L;

	private DateFormat(){
		
	}
	
	public static String dataAtualString() {
		Date data = new Date();
		return new SimpleDateFormat("ddMMyyyyHHmm").format(data);
	}

	public static String dataString(Date data, String formato) {
		return new SimpleDateFormat(formato).format(data);
	}

	public static Date dataXMLGregCal(XMLGregorianCalendar xmlGC) {

		if (xmlGC == null) {
			return null;
		} else {
			return xmlGC.toGregorianCalendar().getTime();
		}
	}
	
	

}
