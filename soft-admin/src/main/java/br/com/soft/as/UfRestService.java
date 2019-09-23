package br.com.soft.as;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class UfRestService {

    private int HTTP_COD_SUCESSO = 200;
    private HttpURLConnection con;
    private URL url;
    private String URL_WS = "http://ibge.herokuapp.com/estado/UF";

    public HttpURLConnection conectaWS() throws Exception {
        url = null;
        con = null;
        try {
            url = new URL(URL_WS);
            con = (HttpURLConnection) url.openConnection();

            if (con.getResponseCode() != HTTP_COD_SUCESSO) {
                throw new RuntimeException("HTTP error code : " + con.getResponseCode());
            }

        } catch (MalformedURLException e) { //Exception para URL
            e.printStackTrace();

        } catch (IOException e) { // Exception para connection
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return con;

    }
}
