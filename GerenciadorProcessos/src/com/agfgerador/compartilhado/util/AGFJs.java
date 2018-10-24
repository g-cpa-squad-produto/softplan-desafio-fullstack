package com.agfgerador.compartilhado.util;

import java.util.List;
import org.zkforge.json.simple.JSONArray;
import org.zkforge.json.simple.JSONObject;
import org.zkoss.zul.A;
public class AGFJs {
	@SuppressWarnings("unchecked")
	public static JSONArray  removeItenBF(JSONArray barraFerramentasButtons,List<String> elementos)
	{	JSONArray novaBF = new JSONArray();
		JSONObject elemento;
		for(int x=0;x<barraFerramentasButtons.size();x++)
		{	elemento = (JSONObject) barraFerramentasButtons.get(x);
			if(!elementos.contains(elemento.get("nome")))
				novaBF.add(elemento);
				
		}	
		return novaBF;
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
	
	
	
}
