package com.agfgerador.autenticacao.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Entity
@Table(name="agf_componente",schema="agfpb_autenticacao",
uniqueConstraints=@UniqueConstraint(columnNames={"nome","sistema"}))
public class Componente extends ObjetoPadrao {

	private static final long serialVersionUID = 1L;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=60)
	private String nome;
	
	@Column(length=255)
	private String descricao;
	
	@Column(length=255)
	private String url;
	
	@Enumerated(EnumType.STRING)
	private TipoSistema sistema;
	
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=60)
	private String menu;
	
	@Column(length=555)
	private String informacao;
	
    @ManyToOne
    @JoinColumn(name="tipomenu_id",nullable=false)
    private Tipomenu tipomenu;
    
    @ManyToOne
    @JoinColumn(name="icone_id",nullable=true)
    private Icones icone;
	
	public String toString()
	{
		return descricao+" ["+nome+"]";
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoSistema getSistema() {
		return sistema;
	}

	public void setSistema(TipoSistema sistema) {
		this.sistema = sistema;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInformacao() {
		return informacao;
	}

	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}
	
	public Tipomenu getTipomenu() {
	       return tipomenu;
	     }
	     
	public void setTipomenu(Tipomenu tipomenu) {
	       this.tipomenu = tipomenu;
	     }
	     
	public Icones getIcone() {
	       return icone;
	     }
	     
	public void setIcone(Icones icone) {
	       this.icone = icone;
	     }

	@Override
	public int compare(Object o1, Object o2) {

		return 0;
	}

	@Override
	public String toLog() {

		return null;
	}

}
