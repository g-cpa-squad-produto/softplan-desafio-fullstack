package com.agfgerador.compartilhado.util;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.A;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Span;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.service.ServicePadrao;

import org.zkforge.json.simple.JSONArray;
import org.zkforge.json.simple.JSONObject;

public class AGFComponente {

	public static ListModel getModelCombo(ServicePadrao servico)
	{	ListModel ls = null;
		ls = new ListModelList(servico.findAll());
		return ls;
	}

	@SuppressWarnings("rawtypes")
	public static ListModel getModelCombo(Enum [] valores)
	{	ListModel ls = null;
		ls = new ListModelList(valores);
		return ls;
	}

	public static ListModel getModelCombo(List<ObjetoPadrao> objs)
	{	ListModel ls = null;
		ls = new ListModelList(objs);
		return ls;
	}

	public static void showMessage(String tipo, String mensagem) {
		Clients.evalJavaScript("showMessage('"+tipo+"', '"+mensagem+"')");  
	}

	public static Popup setTooltiptext(String mensagem, Window window) {
		  Popup popup;
		  Label texto;
		  popup = new Popup();
		  texto = new Label();
		  popup.setClass("tooltiptext");
		  texto.setValue(mensagem);
		  popup.appendChild(texto);
		  window.appendChild(popup);
		  return popup;
	 }

	public static Popup setTooltiptext(String mensagem, String width, Window window) {
		  Popup popup;
		  Label texto;
		  popup = new Popup();
		  texto = new Label();
		  popup.setClass("tooltiptext");
		  popup.setWidth(width);
		  texto.setValue(mensagem);
		  popup.appendChild(texto);
		  window.appendChild(popup);
		  return popup;
	 }

	public static Object convertValueElement(InputElement elemento)
	{	Object value = null;
		if(elemento.getClass().equals(Longbox.class))
			value =((Longbox)elemento).getValue();
		else if(elemento.getClass().equals(Doublebox.class))
			value =((Doublebox)elemento).getValue();
		else if(elemento.getClass().equals(Datebox.class))
			value =((Datebox)elemento).getValue();
		else if(elemento.getClass().equals(Textbox.class))
			value =((Textbox)elemento).getValue();
		else if(elemento.getClass().equals(Intbox.class))
			value =((Intbox)elemento).getValue();
		
		return value;
	}

	public static Map<String,InputElement> onOKlongbox(Longbox longbox,Combobox combobox,ServicePadrao servico){
		Map<String,InputElement> elementos = new HashMap<String, InputElement>();
		if(longbox.getValue()!=null)
		{	ObjetoPadrao obj = servico.findById(longbox.getValue());
			if(obj == null){
				combobox.setValue("");		
			}else{
				combobox.setValue(obj.toString());
			}
		}
	    else
		{
	    	combobox.setValue("");
	    	longbox.setValue(null);
		}
		elementos.put("combobox", combobox);
		elementos.put("longbox", longbox);
		return elementos;
	}

	public static Map<String,InputElement> onSelectcombobox(Longbox longbox,Combobox combobox){
		Map<String,InputElement> elementos = new HashMap<String, InputElement>();
		if(combobox.getSelectedItem()!=null)
		{	ObjetoPadrao obj = (ObjetoPadrao) combobox.getSelectedItem().getValue();
			longbox.setValue(obj.getId());
		}
		else
		{
			combobox.setValue("");
			longbox.setValue(null);
		}
		
		elementos.put("combobox", combobox);
		elementos.put("longbox", longbox);
		return elementos;
	}

	public static Map<String,Object> uploadImage(Media media,Image imagem,Integer size,Div pics)
	{	Map<String,Object> ret = new HashMap<String, Object>();
		String erro = "";
		if(media.getByteData().length>size){
			imagem = null;
			erro=" Imagem ultrapassou o tamanho máximo aceito.";
		}
		else if (media instanceof org.zkoss.image.Image) 
		{	
			imagem.setContent((org.zkoss.image.Image)media);
			imagem.setParent(pics);
			imagem.setHeight("100px");
			imagem.setWidth("100px");
	    
		} 
		else{
			imagem = null;
			erro="Erro ao carregar a imagem.";
		}
			
		ret.put("erro", erro);
		ret.put("imagem", imagem);
		return ret;

	}
	
	public static void exportar(String nomeTela,Listbox listbox) throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(nomeTela);
		HSSFRow row = sheet.createRow(0);
		HSSFFont fontRedBold = workbook.createFont();
		HSSFFont fontNormal = workbook.createFont();
		fontRedBold.setColor(HSSFFont.COLOR_RED);
		fontRedBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontNormal.setColor(HSSFFont.COLOR_NORMAL);
		fontNormal.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		HSSFCellStyle cellStyleRedBold = workbook.createCellStyle();
		HSSFCellStyle cellStyleNormal = workbook.createCellStyle();
		cellStyleRedBold.setFont(fontRedBold);
		cellStyleNormal.setFont(fontNormal);

		int i = 0;
		row = sheet.createRow(0);

		for (Object head : listbox.getHeads()) {

			if(head.getClass() == Listhead.class){
				for (Object header : ((Listhead) head).getChildren()) {
					String h = ((Listheader) header).getLabel();
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(cellStyleRedBold);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(h);
					i++;
				}
			}
		}

		int x = 1;
		int y = 0;
		for (Object item : listbox.getItems()) {
			row = sheet.createRow(x);
			y = 0;
			for (Object lbCell : ((Listitem) item).getChildren()) {
				String h;
				h = ((Listcell) lbCell).getLabel();
				HSSFCell cell = row.createCell(y);
				cell.setCellStyle(cellStyleNormal);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(h);
				y++;
			}
			x++;
		}
		String str = nomeTela.toLowerCase()+".xls";
		FileOutputStream fOut = new FileOutputStream(str);
		
		workbook.write(fOut);
		fOut.flush();

		fOut.close();

		File file = new File(str);
		Filedownload.save(file, "XLS");		
	}

	 public static void fileDownload(File arquivo,String formato) 
	 {	try {	
		 	Filedownload.save(arquivo, formato);
	 	} 
		catch (IOException ex) {
			 ex.printStackTrace();
		 }		 
	 }

	 public static  Listcell getAListcell(ObjetoPadrao obj,String tooltiptext ,String metodo,String label,String classe,boolean temSpan) {
		A bt = new A();
		if(temSpan){
			Span glyphicon = new Span();
			glyphicon.setClass("glyphicon glyphicon-trash");
			glyphicon.setTooltiptext(tooltiptext);
			bt.appendChild(glyphicon);
		}
		else{
			bt.setClass(classe);
			bt.setLabel(label);
			bt.setTooltiptext(tooltiptext);
		}
		bt.addForward("onClick",bt.getTarget(), metodo,obj);
		Listcell coluna = new Listcell();
		coluna.appendChild(bt);
		return coluna;
	}
	 
		@SuppressWarnings("unchecked")
		public static JSONArray addItemBF(JSONArray barraFerramentasButtons,A btn, String contexto) {
			JSONObject button = new JSONObject();
			button.put("nome", btn.getId());
			button.put("uuid",btn.getUuid()); 
			button.put("contexto",contexto); 
			barraFerramentasButtons.add(button);
			return barraFerramentasButtons;
		}

		public static  Listcell getAListcellicon(ObjetoPadrao obj,String tooltiptext ,String metodo,String label,String classe,boolean temSpan,String color) {
			A bt = new A();
			if(temSpan){
				Span glyphicon = new Span();
				glyphicon.setClass("glyphicon glyphicon-trash");
				glyphicon.setTooltiptext(tooltiptext);
				bt.appendChild(glyphicon);
				bt.setStyle("color:"+color+";");
			}
			else{
				bt.setClass(classe);
				bt.setLabel(label);
				bt.setTooltiptext(tooltiptext);
				bt.setStyle("color:"+color+";");
			}
			bt.addForward("onClick",bt.getTarget(), metodo,obj);
			Listcell coluna = new Listcell();
			coluna.appendChild(bt);
			return coluna;
		}
}
