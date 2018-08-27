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
public class ProcessResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1517670884022709952L;
	
	private Long id;
	private String summary;
	private String description;
	private Long createdBy;
	private Long finishedBy;
	
	@JsonCreator
	public ProcessResponse(
			@JsonProperty("id") final Long id,
			@JsonProperty("summary") final String summary,
			@JsonProperty("description") final String description,
			@JsonProperty("createdBy") final Long createdBy,
			@JsonProperty("finishedBy") final Long finishedBy) {
		this.id = id;		
		this.summary = summary;
		this.description = description;
		this.createdBy = createdBy;
		this.finishedBy = finishedBy;
	}
}