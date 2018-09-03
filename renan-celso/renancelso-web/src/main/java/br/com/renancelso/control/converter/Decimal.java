/*
 * Decimal.java
 *
 * Created on 19 de Novembro de 2007, 17:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.com.renancelso.control.converter;

import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Renan Celso
 */
@FacesConverter(value = "decimal", forClass = Double.class)
public class Decimal implements Converter {

	@Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String valorTela) {
        Double valorRetorno;
        if (valorTela == null || "".equals(valorTela)) {
            return null;
        } else {
        	String valoFinal;
        	valoFinal = valorTela.replace(".", "");
        	valoFinal = valoFinal.replace(",", ".");
            valorRetorno = Double.valueOf(valoFinal);
            return valorRetorno;
        }

    }

	@Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object valorTela) {
        if (valorTela == null || "".equals(valorTela)) {
            return null;
        } else {
            NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            return nf.format(Double.valueOf(valorTela.toString()));
        }
    }
}
