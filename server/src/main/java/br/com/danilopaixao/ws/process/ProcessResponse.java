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
	private Long idCreatedBy;
	private String loginCreatedBy;
	private Long idFinishedBy;
	private String loginFinishedBy;
	
	@JsonCreator
	public ProcessResponse(
			@JsonProperty("id") final Long id,
			@JsonProperty("summary") final String summary,
			@JsonProperty("description") final String description,
			@JsonProperty("idCreatedBy") final Long idCreatedBy,
			@JsonProperty("loginCreatedBy") final String loginCreatedBy,
			@JsonProperty("idFinishedBy") final Long idFinishedBy,
			@JsonProperty("loginFinishedBy") final String loginFinishedBy) {
		this.id = id;		
		this.summary = summary;
		this.description = description;
		this.idCreatedBy = idCreatedBy;
		this.loginCreatedBy = loginCreatedBy;
		this.idFinishedBy = idFinishedBy;
		this.loginFinishedBy = loginFinishedBy;
	}
}