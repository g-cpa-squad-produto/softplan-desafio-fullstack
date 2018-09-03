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
@FacesConverter(value = "dinheiro", forClass = Double.class)
public class Dinheiro implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String valorTela) {
        Double valorRetorno;

        if (valorTela == null || "".equals(valorTela)) {
            return null;
        } else {
        	String valorFinal;
        	valorFinal = valorTela.replace("R$", "");
        	valorFinal = valorFinal.replace(" ", "");

        	valorFinal = valorFinal.replace(".", "");
        	valorFinal = valorFinal.replace(",", ".");
            valorRetorno = Double.valueOf(valorFinal);
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
            String valorFinal;
            valorFinal = valorTela.toString().replace("R$", "");

            return "R$ " + nf.format(Double.valueOf(valorFinal));
        }


    }
}
