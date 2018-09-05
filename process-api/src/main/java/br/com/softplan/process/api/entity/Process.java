package br.com.softplan.process.api.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.softplan.process.api.enums.PriorityEnum;
import br.com.softplan.process.api.enums.StatusEnum;

@Document
public class Process {

	@Id
	private String id;

	private Integer number;
	
	@DBRef(lazy = true)
	private User user;
	
	private Date date;
	
	private String subject;
	
	private User assignedUser;
	
	private String description;
	
	private String attachment;
	
	private StatusEnum status;
	
	private PriorityEnum priority;
	
	@Transient
    private List<AnalysisProcess> analyzes;
    
    @Transient
    private List<ChangeStatus> changes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public PriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}

	public List<ChangeStatus> getChanges() {
		return changes;
	}

	public void setChanges(List<ChangeStatus> changes) {
		this.changes = changes;
	}

	public List<AnalysisProcess> getAnalyzes() {
		return analyzes;
	}

	public void setAnalyzes(List<AnalysisProcess> analyzes) {
		this.analyzes = analyzes;
	}   	    
	
}
