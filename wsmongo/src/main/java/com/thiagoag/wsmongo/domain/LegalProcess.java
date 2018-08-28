package com.thiagoag.wsmongo.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.thiagoag.wsmongo.DTO.AuthorDTO;
import com.thiagoag.wsmongo.DTO.DecisionDTO;

@Document
public class LegalProcess implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private Date date;
	private String processNumber;
	private String body;
	private int status;
	private AuthorDTO author;
	private DecisionDTO decision;
	
	public LegalProcess() {

	}

	public LegalProcess(String id, Date date, String processNumber, String body, int status, AuthorDTO author,
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LegalProcess other = (LegalProcess) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
