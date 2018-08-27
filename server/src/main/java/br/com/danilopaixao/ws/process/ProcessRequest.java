package br.com.danilopaixao.ws.process;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class ProcessRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1517670884022709952L;
	
	private String summary;
	private String description;
	private Long idUserCreatedBy;
	private Long idUserFinishedBy;
	
	@JsonCreator
	public ProcessRequest(
			@JsonProperty("summary") final String summary,
			@JsonProperty("description") final String description,
			@JsonProperty("idUserCreatedBy") final Long idUserCreatedBy,
			@JsonProperty("idUserFinishedBy") final Long idUserFinishedBy) {
		this.summary = summary;
		this.description = description;
		this.idUserCreatedBy = idUserCreatedBy;
		this.idUserFinishedBy = idUserFinishedBy;
	}
}
