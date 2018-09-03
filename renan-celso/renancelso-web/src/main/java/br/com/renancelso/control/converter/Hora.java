package br.com.renancelso.control.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Renan Celso
 */
@FacesConverter(value = "hora", forClass = Date.class)
public class Hora implements Converter {

   
	@Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String valorTela)  {
        
		Date valorRetorno;
        String formato = "HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        try {
            valorRetorno = formatter.parse(valorTela);
            return valorRetorno;
        } catch (ParseException ex) {        	
        	return null;
        }


    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object valorTela) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        return dateFormat.format(valorTela);
    }
}
