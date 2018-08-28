package com.thiagoag.wsmongo.DTO;

import java.io.Serializable;
import java.util.Date;

public class LegalProcessDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date date;
	private String processNumber;
	private String body;
	private int status;
	private AuthorDTO author;
	private DecisionDTO decision;
	
	
	public LegalProcessDTO() {
			
	}
	
	public LegalProcessDTO(String id, Date date, String processNumber, String body, int status, AuthorDTO author,
			DecisionDTO decision) {
		super();
		this.id = id;
		this.date = date;
		this.processNumber = processNumber;
		this.body = body;
		this.status = status;
		this.author = author;
		this.decision = decision;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProcessNumber() {
		return processNumber;
	}
	public void setProcessNumber(String processNumber) {
		this.processNumber = processNumber;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public AuthorDTO getAuthor() {
		return author;
	}
	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
	public DecisionDTO getDecision() {
		return decision;
	}
	public void setDecision(DecisionDTO decision) {
		this.decision = decision;
	}
	
	
}
