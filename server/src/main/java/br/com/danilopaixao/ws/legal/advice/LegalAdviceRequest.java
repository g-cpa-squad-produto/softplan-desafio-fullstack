package br.com.danilopaixao.ws.legal.advice;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class LegalAdviceRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3471504460137480458L;
	
	private Long processId;
	private String description;
	private Long idCreatedBy;
	private String loginCreatedBy;
	private Long idFinishedBy;
	private String loginFinishedBy;
	private Long idResponsableFor;
	private String loginResponsableFor;
	
	@JsonCreator
	public LegalAdviceRequest(
			@JsonProperty("processId") final Long processId,
			@JsonProperty("description") final String description,
			@JsonProperty("idCreatedBy") final Long idCreatedBy,
			@JsonProperty("loginCreatedBy") final String loginCreatedBy,
			@JsonProperty("idFinishedBy") final Long idFinishedBy,
			@JsonProperty("loginFinishedBy") final String loginFinishedBy,
			@JsonProperty("idResponsableFor") final Long idResponsableFor,
			@JsonProperty("loginResponsableFor") final String loginResponsableFor) {
		this.processId = processId;
		this.description = description;
		this.idCreatedBy = idCreatedBy;
		this.loginCreatedBy = loginCreatedBy;
		this.idFinishedBy = idFinishedBy;
		this.loginFinishedBy = loginFinishedBy;
		this.idResponsableFor = idResponsableFor;
		this.loginResponsableFor = loginResponsableFor;
	}
	
	
}
