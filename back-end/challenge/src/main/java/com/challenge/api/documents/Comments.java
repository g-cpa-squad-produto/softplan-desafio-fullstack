package com.challenge.api.documents;

import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comments {
	@Id
	private String id;
	private String comment;
	private String userID;
	private String ProccessID;
	
	public Comments() {
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@NotEmpty(message = "Usuario não pode ser vazio")
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	@NotEmpty(message = "Processo não pode ser vazio")
	public String getProccessID() {
		return ProccessID;
	}

	public void setProccessID(String proccessID) {
		ProccessID = proccessID;
	}
}
