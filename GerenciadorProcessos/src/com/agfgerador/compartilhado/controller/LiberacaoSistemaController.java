package com.agfgerador.compartilhado.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent; 
import org.zkoss.zk.ui.util.GenericForwardComposer;  
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.agfgerador.autenticacao.domain.Compativeis;
import com.agfgerador.autenticacao.service.CompativeisService;
import com.agfgerador.compartilhado.util.AGFComponente;

public class LiberacaoSistemaController extends GenericForwardComposer {

	private static final long serialVersionUID = 1L;

	private Textbox textSenha;
	private Div mensagem;
	private Label txtMensagem;
	private Timer contador;
	private DashboardPadrao controller;
	private CompativeisService compativeisService;
	private Window window;


	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		win.setAttribute("controller",this);  
		window = (Window) win;
	}  

	public void onCreate$windowLiberacao(CreateEvent event) {
		inicializaVariaveis(); 
		
	}

	public void inicializaVariaveis() {
		controller = (DashboardPadrao) window.getRoot().getAttribute("controller");
		textSenha.setValue(null);
		textSenha.setFocus(true);
		bloquearSistema();
	} 

	public void onClick$btLiberar(){
		Compativeis compativeisAux = null;
		try{
			compativeisAux = compativeisService.LoadBySistema(Integer.parseInt(controller.codSistema));
			if(!textSenha.getValue().equals("")){
				compativeisAux.setSenhadeliberacao(textSenha.getValue());
				compativeisService.update(compativeisAux);
				mensagem.setClass("mensagem-erro");
				if(bloquearSistema())
					AGFComponente.showMessage("sucesso", "Sistema liberado.");
				else{
					txtMensagem.setValue("Senha inválida");
					contador.start();
					mensagem.setClass("mensagem-erro mostrar");
				}
			}
			else{
				txtMensagem.setValue("Informe a senha");
				contador.start();
				mensagem.setClass("mensagem-erro mostrar");
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void onOK$textSenha(){
		onClick$btLiberar();
	}
	public boolean bloquearSistema()
	{	boolean ret = true;
		if((boolean)session.getAttribute("iscompativelbaseapp")){
			ret = SenhaDoSistemaValida();
			setVisibleInclude(!ret);
			if(ret)
				controller.verificaAvisos();
		}else{
			setVisibleInclude(false);
		}	
		
		return ret; 
	}

	public void setVisibleInclude(boolean isvisivel){
		controller.includeLiberacao.setVisible(isvisivel);
	}
	

	@SuppressWarnings("unused")
	public boolean SenhaDoSistemaValida(){
		boolean resultado = true;	
		String senha = null;	
		try{
			senha = compativeisService.LoadBySistema(Integer.parseInt(controller.codSistema)).getSenhadeliberacao();
		}catch(Exception e){
			e.printStackTrace();
		}	

		if(senha == null) 
			senha="9X9X2X000481X3X9X1XX";

		resultado  = MB_SenhaValida(senha, "MBS"+controller.codSistema);
		return true;
		//return resultado;
	}

	public boolean MB_SenhaValida(String PassWord, String NSis){
		
		boolean ret = true;
		boolean msgdoprogramador = false;
		Date DataV,DataI;
		String IDCli, IDSis;
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
		Date agora = new Date();

		DataV=MB_GetDV(PassWord);
		try {
			if(sdf1.parse("01/01/1950")==DataV)
			{	if(msgdoprogramador) 
				{	System.out.println("Erro: Validando Data de Vencimento...");
					ret = false;
				}
			} 
		}
		catch (ParseException e1) {
			ret = false;
			e1.printStackTrace();
		}

		DataI=MB_GetDI(PassWord);
		try {
			if(sdf1.parse("01/01/9999")==DataI){
				if(msgdoprogramador) System.out.println("Erro: Validando Data Inicial...");
				return false;}
		} catch (ParseException e) {

			e.printStackTrace();
		}

		IDCli=MB_GetIdCli(PassWord);
		if("XXX"==IDCli){					
			if(msgdoprogramador) System.out.println("Erro: Validando Id do Cliente...");
			return false;}

		IDSis=MB_GetIdSis(PassWord);
		if("XXX00"==IDSis){					
			if(msgdoprogramador) System.out.println("Erro: Validando Id do Sistema...");
			return false;}

		if (agora.before(DataI) || agora.after(DataV)) {					
			if(msgdoprogramador) System.out.println("Erro: Comparando a data atual com o período da senha...");
			return false;}

		if(!NSis.equals(IDSis)){					
			if(msgdoprogramador) System.out.println("Erro: Comparando o código do sistema com o código da senha...");
			return false;}

		if(!controller.pGerais.getAcod().equals(IDCli)){					
			if(msgdoprogramador) System.out.println("Erro: Comparando o código do cliente com o código da senha...");
			return false;}

		return ret;
	}

	private Date MB_GetDV(String PassWord) 
	{
		Date resultado = null;

		Integer DiaV,MesV,AnoV;
		char[] MesFull = {'X','X','X'};
		String MesFullTemp;
		int LetCod;
		String SenhaCod;
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");

		try {
			resultado=sdf1.parse("01/01/1950");
		} catch (ParseException e1) {			
			e1.printStackTrace();
		}        
		try{
			SenhaCod=PassWord;
			DiaV=Integer.parseInt(SenhaCod.substring(0, 1),10) +Integer.parseInt(SenhaCod.substring(8, 9),10)*10;
			LetCod= Integer.valueOf(SenhaCod.charAt(1)) - (DiaV % 10);
			if(LetCod<65) 
				LetCod=LetCod+26;
			
			MesFull[0]= (char) LetCod;
			LetCod=Integer.valueOf(SenhaCod.charAt(3))-(DiaV % 10);
			if(LetCod<65)
				LetCod=LetCod+26;
			
			MesFull[1]= (char) LetCod;
			LetCod=Integer.valueOf(SenhaCod.charAt(5))-(DiaV % 10);
			if(LetCod<65)
				LetCod=LetCod+26;

			MesFull[2]=(char) LetCod;
			MesFullTemp=String.valueOf(MesFull);

			MesV=0;
			if( MesFullTemp.equals("JAN"))  MesV=1;        if( MesFullTemp.equals("FEV"))  MesV=2;
			if( MesFullTemp.equals("MAR"))  MesV=3;        if( MesFullTemp.equals("ABR"))  MesV=4;
			if( MesFullTemp.equals("MAI"))  MesV=5;        if( MesFullTemp.equals("JUN"))  MesV=6;
			if( MesFullTemp.equals("JUL"))  MesV=7;        if( MesFullTemp.equals("AGO"))  MesV=8;
			if( MesFullTemp.equals("SET"))  MesV=9;        if( MesFullTemp.equals("OUT"))  MesV=10;
			if( MesFullTemp.equals("NOV"))  MesV=11;        if( MesFullTemp.equals("DEZ"))  MesV=12;
			if(MesV==0) {	        	 
				return resultado;	          
			} 
			AnoV=2000+(Integer.parseInt(SenhaCod.substring(7, 8),10)*100+
					Integer.parseInt(SenhaCod.substring(4, 5),10)*10+
					Integer.parseInt(SenhaCod.substring(2, 3),10))-DiaV-MesV;

			resultado=sdf1.parse(String.valueOf(DiaV)+"/"+String.valueOf(MesV)+"/"+String.valueOf(AnoV));

		}catch(Exception e){
			return resultado;
		}	

		return resultado;	         

	}

	private String MB_GetIdSis(String PassWord){
		String resultado,SenhaCod;
		Integer    DigS;
		char[] SisFull = {'X','X','X','0','0'};
		int LetCod;

		resultado="XXX00";

		try{
			SenhaCod=PassWord;
			DigS=Integer.parseInt(SenhaCod.substring(11, 12),10);

			LetCod= Integer.valueOf(SenhaCod.charAt(14)) - DigS;
			if(LetCod<65) LetCod=LetCod+26;	    	         
			SisFull[0]= (char) LetCod;

			LetCod= Integer.valueOf(SenhaCod.charAt(16)) - DigS;
			if(LetCod<65) LetCod=LetCod+26;	    	         
			SisFull[1]= (char) LetCod;
			
			LetCod= Integer.valueOf(SenhaCod.charAt(18)) - DigS;
			if(LetCod<65) LetCod=LetCod+26;	    	         
			SisFull[2]= (char) LetCod;

			LetCod= Integer.valueOf(SenhaCod.charAt(17)) - DigS;
			if(LetCod<48) LetCod=LetCod+10;	    	         
			SisFull[3]= (char) LetCod;

			LetCod= Integer.valueOf(SenhaCod.charAt(13)) - DigS;
			if(LetCod<48) LetCod=LetCod+10;	    	         
			SisFull[4]= (char) LetCod;


			resultado=String.valueOf(SisFull);
		}catch(Exception e){   
			return resultado;
		}
		return resultado;	         
	}

	private String MB_GetIdCli(String PassWord){
		String resultado,SenhaCod;
		Integer DigC;
		char[] CliFull = {'X','X','X'};
		int LetCod;

		resultado="XXX";
		try{
			SenhaCod=PassWord;

			DigC=Integer.parseInt(SenhaCod.substring(10, 11),10);

			LetCod= Integer.valueOf(SenhaCod.charAt(19)) - DigC;
			if(LetCod<65) LetCod=LetCod+26;	    	         
			CliFull[0]= (char) LetCod;

			LetCod= Integer.valueOf(SenhaCod.charAt(12)) - DigC;
			if(LetCod<65) LetCod=LetCod+26;	    	         
			CliFull[1]= (char) LetCod;

			LetCod= Integer.valueOf(SenhaCod.charAt(15)) - DigC;
			if(LetCod<48) LetCod=LetCod+10;	    	         
			CliFull[2]= (char) LetCod;


			resultado=String.valueOf(CliFull);

		}catch(Exception e){return resultado;	                 
		}
		return resultado;
	}

	@SuppressWarnings("deprecation")
	private Date MB_GetDI(String PassWord)
	{	Date resultado = null;
		String SenhaCod;
		Integer Validade,DiaV;
		Date DataV;
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");

		try {
			resultado=sdf1.parse("01/01/9999");
		} catch (ParseException e1) {			
			e1.printStackTrace();
		}      

		try{
			SenhaCod=PassWord;
			DataV=MB_GetDV(PassWord);

			DiaV=DataV.getDate();	                 

			if(DiaV % 10>5)
				Validade=Integer.parseInt(SenhaCod.substring(9, 10),10)*10+Integer.parseInt(SenhaCod.substring(6, 7),10);
			else
				Validade=Integer.parseInt(SenhaCod.substring(6, 7),10)*10+Integer.parseInt(SenhaCod.substring(9, 10),10);

			Calendar agora = Calendar.getInstance();
			agora.setTime(DataV);
			agora.add(Calendar.DAY_OF_YEAR, -Validade);
			resultado = agora.getTime();
		} catch(Exception e){
			return resultado;
		}
		return resultado;
	}
	
}
