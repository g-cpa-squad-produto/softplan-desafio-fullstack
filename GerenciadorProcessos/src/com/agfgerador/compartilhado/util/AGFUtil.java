package com.agfgerador.compartilhado.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.agfgerador.autenticacao.domain.Email;
import com.agfgerador.autenticacao.domain.Usuario;

public class AGFUtil {	
	public static String  MD5(String senha) {
	    PasswordEncoder encoder = new Md5PasswordEncoder();
	    return encoder.encodePassword(senha, null);
	}
	
	public static String geraCodigo(int tamanho)
	{  String codigo="";
		for (int x=0;x<tamanho;x++)
			codigo=codigo+geraDigito();
		return codigo;
	}
	
	
	public static  Integer  geraDigito()
	{	Random r = new Random();  
		return r.nextInt(9);  
	}

	public static  String  geraChar()
	{	Random r = new Random();  
	    String letras [] = {"a","b","c","d","e","f","g","h","i","j","l","m","n","o","p","q","r","s","t","u","v","x","z","k","y","w"};
	    int indice = r.nextInt(25);   
	    return letras[indice];
	}

	public static String geraAlfanumerico(int tamanho)
	 {   String senha="";
		 for(int x=0;x<tamanho;x++)
		 {  
			 if(geraUmDigito()==0)
				 senha=senha+geraChar();
			 else
				 senha=senha+geraDigito();
		 }
		 return senha;
	 }

	 public static  Integer  geraUmDigito()
	 {	Random r = new Random();  
		    // retorna um número aleatório de 0 - 9
			return r.nextInt(2);  
	 }

	 public static boolean verificaTamanhoCampo(String string,int tamanhoMinimo){ 
		boolean ret = true;
		if(string != null){
			if(string.length() < tamanhoMinimo){
				ret =  false;
			}
			else{
				ret =  true;
			}
		}
		
		return ret;	
	}
	 
	 
	 private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}; //validacao do CNPJ
	 private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	
	public static boolean isValidCNPJ(String cnpj) { 
		cnpj=cnpj.replace("/", "").replace(".", "").replace("-","").replace("_", "");
		if ((cnpj==null)||(cnpj.length()!=14)||(cnpj.equalsIgnoreCase("00000000000000"))) 
			return false;
		Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
	}

	 public static  boolean isValidCPF(String cpf) 
	 { 
	    cpf=cpf.replace("-", "").replace(".", "").replace("_", "");
	   
		 if ((cpf==null) || (cpf.length()!=11)||(cpf.equalsIgnoreCase("00000000000"))||(cpf.equalsIgnoreCase("11111111111"))||(cpf.equalsIgnoreCase("22222222222"))||(cpf.equalsIgnoreCase("33333333333"))||(cpf.equalsIgnoreCase("44444444444"))||(cpf.equalsIgnoreCase("55555555555"))||(cpf.equalsIgnoreCase("66666666666"))||(cpf.equalsIgnoreCase("77777777777"))||(cpf.equalsIgnoreCase("88888888888"))||(cpf.equalsIgnoreCase("99999999999"))) 
	    	  return false;
	
	     Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
	     Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
	     return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	   }
		
	private static int calcularDigito(String str, int[] peso) { 
		int soma = 0;
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
			soma += digito*peso[peso.length-str.length()+indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
	 public static void enviaEmail(Email email,Usuario usuario,String titulo,String assunto,String msg) throws EmailException {
		 HtmlEmail mail = new ImageHtmlEmail();
		 mail.setHostName(email.getServidor()); // o servidor SMTP para envio do e-mail
    	 mail.addTo(usuario.getEmail());
         mail.setFrom(email.getEmail(), titulo); // remetente
         mail.setSubject(assunto); // assunto do e-mail 
         mail.setAuthentication(email.getLogin(), email.getSenha());  // senha do remetente
         mail.setHtmlMsg(msg); // seta a mensagem de formato html criada na string htmlEmailTemplate
         mail.setTextMsg("Seu e-mail nao suporta mensagens com formato HTML."); // mensagem alternativa
         mail.setSmtpPort(email.getPorta());  
         mail.setSSL(true);  
         mail.setTLS(true);
         //email.setTo(listaDeEmails); // seta destinatários
         //email.setCc(listaDeEmails);
         mail.send(); // envia o email 
		
	    	
	    }

	 public static boolean isValidEmail(String e)
	 {	boolean ret = true;
	    Pattern padrao = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher email = padrao.matcher(e);
		if(!email.find())
			ret = false;
		return ret;
	
	 }

	public static String formatoDouble(Double valor){
		return new DecimalFormat("#,##0.00").format(valor); 
	}

	public static String formatoInteger(Double valor){
		return new DecimalFormat("#,##0").format(valor); 
	}

	public static String formatoDate(Date dt){
		return new SimpleDateFormat("dd/MM/yyyy").format(dt); 
	}

	public static String formatHora(Date dt){
		return new SimpleDateFormat("HH:mm").format(dt);
	}

	public static String formatoDateHora(Date dt){
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dt); 
	}

	public static boolean validaCns(String cns){
		if (cns.trim().length() != 15){
			return(false);
			}

			float soma;
			float resto, dv;
			String pis = new String("");
			String resultado = new String("");
			pis = cns.substring(0,11);

			soma = ((Integer.valueOf(pis.substring(0,1)).intValue()) * 15) +
			((Integer.valueOf(pis.substring(1,2)).intValue()) * 14) +
			((Integer.valueOf(pis.substring(2,3)).intValue()) * 13) +
			((Integer.valueOf(pis.substring(3,4)).intValue()) * 12) +
			((Integer.valueOf(pis.substring(4,5)).intValue()) * 11) +
			((Integer.valueOf(pis.substring(5,6)).intValue()) * 10) +
			((Integer.valueOf(pis.substring(6,7)).intValue()) * 9) +
			((Integer.valueOf(pis.substring(7,8)).intValue()) * 8) +
			((Integer.valueOf(pis.substring(8,9)).intValue()) * 7) +
			((Integer.valueOf(pis.substring(9,10)).intValue()) * 6) +
			((Integer.valueOf(pis.substring(10,11)).intValue()) * 5);

			resto = soma % 11;
			dv = 11 - resto;

			if (dv == 11){
			dv = 0;
			}

			if (dv == 10){
			soma = ((Integer.valueOf(pis.substring(0,1)).intValue()) * 15) +
			((Integer.valueOf(pis.substring(1,2)).intValue()) * 14) +
			((Integer.valueOf(pis.substring(2,3)).intValue()) * 13) +
			((Integer.valueOf(pis.substring(3,4)).intValue()) * 12) +
			((Integer.valueOf(pis.substring(4,5)).intValue()) * 11) +
			((Integer.valueOf(pis.substring(5,6)).intValue()) * 10) +
			((Integer.valueOf(pis.substring(6,7)).intValue()) * 9) +
			((Integer.valueOf(pis.substring(7,8)).intValue()) * 8) +
			((Integer.valueOf(pis.substring(8,9)).intValue()) * 7) +
			((Integer.valueOf(pis.substring(9,10)).intValue()) * 6) +
			((Integer.valueOf(pis.substring(10,11)).intValue()) * 5) + 2;

			resto = soma % 11;
			dv = 11 - resto;
			resultado = pis + "001" + String.valueOf((int)dv);
			}
			else{
			resultado = pis + "000" + String.valueOf((int)dv);
			}

			if (! cns.equals(resultado)){
			return(false);
			}
			else{
			return(true);
			}
			}
	
	/**
	 * Validar CNS Provisorio
	 * @param dt [cns]
	 * @return String
	 *
	 * @author Arthur Freire
	 */
	public boolean validaCnsProvisorio(String cns){
		if (cns.trim().length() != 15){
			return(false);
			}

			//float dv;
			float resto,soma;

			soma = ((Integer.valueOf(cns.substring(0,1)).intValue()) * 15) +
			((Integer.valueOf(cns.substring(1,2)).intValue()) * 14) +
			((Integer.valueOf(cns.substring(2,3)).intValue()) * 13) +
			((Integer.valueOf(cns.substring(3,4)).intValue()) * 12) +
			((Integer.valueOf(cns.substring(4,5)).intValue()) * 11) +
			((Integer.valueOf(cns.substring(5,6)).intValue()) * 10) +
			((Integer.valueOf(cns.substring(6,7)).intValue()) * 9) +
			((Integer.valueOf(cns.substring(7,8)).intValue()) * 8) +
			((Integer.valueOf(cns.substring(8,9)).intValue()) * 7) +
			((Integer.valueOf(cns.substring(9,10)).intValue()) * 6) +
			((Integer.valueOf(cns.substring(10,11)).intValue()) * 5) +
			((Integer.valueOf(cns.substring(11,12)).intValue()) * 4) +
			((Integer.valueOf(cns.substring(12,13)).intValue()) * 3) +
			((Integer.valueOf(cns.substring(13,14)).intValue()) * 2) +
			((Integer.valueOf(cns.substring(14,15)).intValue()) * 1);

			resto = soma % 11;

			if (resto != 0){
			return(false);
			}
			else{
			return(true);
			}
	}
	
	 /**
	  * Faz a verificação de caracteres especiais
	  * @param e [String parametro]
	  * @return
	  *
	  * @author Arthur Freire
	  */
	 public static boolean isVeriCaractEspecial(String e){	
		 boolean ret = true;
		 Pattern padrao = Pattern.compile("[^a-zZ-Z1-9 ]");
		 Matcher email = padrao.matcher(e);
		 if(email.find()) {
			ret = false;
		 }
		return ret;
	
	 }

}
