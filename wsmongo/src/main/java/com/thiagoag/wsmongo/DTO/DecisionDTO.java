package com.thiagoag.wsmongo.DTO;

import java.io.Serializable;
import java.util.Date;

public class DecisionDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String opinion;
	private String text;
	private Date date;
	private AuthorDTO author;
	
	
	public DecisionDTO() {
		
	}

	public DecisionDTO(String opinion, String text, Date date, AuthorDTO author) {
		super();
		this.opinion = opinion;
		this.text = text;
		this.date = date;
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	
}
