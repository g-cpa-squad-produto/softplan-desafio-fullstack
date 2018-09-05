package br.com.softplan.process.api.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AnalysisProcess {

	@Id
	private String id;
	
	@DBRef	
	private Process process;
	
	@DBRef	
	private User userAnalysis;
	
	private Date date;
	
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

	public User getUserAnalysis() {
		return userAnalysis;
	}

	public void setUserAnalysis(User userAnalysis) {
		this.userAnalysis = userAnalysis;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
}
