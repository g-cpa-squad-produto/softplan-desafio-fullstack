package com.challenge.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;

@Document
public class Proccess {
	@Id
	private String id;
	private String name;
	private String status;
	private String createdBY;
	private ArrayList<String> userID;	
	
	public Proccess() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedBY() {
		return createdBY;
	}

	public void setCreatedBY(String createdBY) {
		this.createdBY = createdBY;
	}

	public ArrayList<String> getUserID() {
		return userID;
	}

	public void setUserID(ArrayList<String> userID) {
		this.userID = userID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		
}
