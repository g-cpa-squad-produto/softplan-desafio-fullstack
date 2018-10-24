package com.agfgerador.autenticacao.domain;

import java.lang.reflect.Field;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Entity
@Table(name = "log_agf",schema="agfpb_autenticacao")
public class Log  extends ObjetoPadrao {
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	@Enumerated(EnumType.STRING)
	private Modulo modulo;
	@Basic
	private String domainName;
	@Basic
	private Long idObject; 
	@Enumerated(EnumType.STRING)
	private Metodo metodo;
	@Basic
	@Type(type="text")
	private String valorAtributos;
	@Basic
	private String usuarioLogin;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public Long getIdObject() {
		return idObject;
	}
	public void setIdObject(Long idObject) {
		this.idObject = idObject;
	}
	public Metodo getMetodo() {
		return metodo;
	}
	public void setMetodo(Metodo metodo) {
		this.metodo = metodo;
	}
	public String getValorAtributos() {
		return valorAtributos;
	}
	public void setValorAtributos(String valorAtributos) {
		this.valorAtributos = valorAtributos;
	}
	public String getUsuarioLogin() {
		return usuarioLogin;
	}
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public int compare(Object arg0, Object arg1) {
		return 0;
	} 
	@SuppressWarnings("rawtypes")
	public String getValorAtributos(Object obj){
		String result = "";
		try {
			
			Class cls = obj.getClass();
			Field fieldlist[] = cls.getDeclaredFields();
			for (int i = 1; i < fieldlist.length; i++) {
				Field fld = fieldlist[i];
				fld.setAccessible(true);
				if(fld.get(obj) != null){
					result += fld.getName() + " : ";
					// Quando um atributo for um outro domain seta-se o id deste objeto - Carlos Pereira
					if((fld.getType().toString().contains("com.agfgerador"))&&(!fld.getType().isEnum()))
						result +=((ObjetoPadrao)fld.get(obj)).getId() + ";\n";
					else 
						result += fld.get(obj) + ";\n";
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public String getValorAtributosSemId(Object obj){
		String result = "";
		try {
			
			Class cls = obj.getClass();
			Field fieldlist[] = cls.getDeclaredFields();
			for (int i = 1; i < fieldlist.length; i++) {
				Field fld = fieldlist[i];
				fld.setAccessible(true);
				if(fld.get(obj) != null){
					result += fld.getName() + " : ";
					// Quando um atributo for um outro domain seta-se o id deste objeto - Carlos Pereira
					if((fld.getType().toString().contains("com.agfgerador"))&&(!fld.getType().isEnum()))
						result +=((ObjetoPadrao)fld.get(obj)).getId() + ";\n";
					else 
						result += fld.get(obj) + ";\n";
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
	@SuppressWarnings("rawtypes")
	public Long getIdObject(Object obj){
		Long id = 0L;
		
		try {
			
			Class cls = obj.getClass();
			Field fieldlist[] = cls.getDeclaredFields();
			Field fld = fieldlist[1];
			fld.setAccessible(true);
		
			id = (Long) fld.get(obj);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public String toLog() {

		return null;
	}
	
}
