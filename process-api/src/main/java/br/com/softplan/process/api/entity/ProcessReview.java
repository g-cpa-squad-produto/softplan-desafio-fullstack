package br.com.softplan.process.api.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProcessReview {

	@Id
	private String id;
	
	@DBRef	
	private Process process;
	
	@DBRef	
	private User user;
	
	private Date dateReview;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateReview() {
		return dateReview;
	}

	public void setDateReview(Date dateReview) {
		this.dateReview = dateReview;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
