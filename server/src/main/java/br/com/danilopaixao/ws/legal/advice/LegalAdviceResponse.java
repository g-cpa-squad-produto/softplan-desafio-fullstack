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
public class LegalAdviceResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3471504460137480458L;
	
	private Long id;
	private Long processId;
	private String processCode;
	private String description;
	private Long idCreatedBy;
	private String loginCreatedBy;
	private Long idFinishedBy;
	private String loginFinishedBy;
	private Long idResponsableFor;
	private String loginResponsableFor;
	private String nameResponsableFor;
	
	@JsonCreator
	public LegalAdviceResponse(
			@JsonProperty("id") final Long id,
			@JsonProperty("processId") final Long processId,
			@JsonProperty("processCode") final String processCode,
			@JsonProperty("description") final String description,
			@JsonProperty("idCreatedBy") final Long idCreatedBy,
			@JsonProperty("loginCreatedBy") final String loginCreatedBy,
			@JsonProperty("idFinishedBy") final Long idFinishedBy,
			@JsonProperty("loginFinishedBy") final String loginFinishedBy,
			@JsonProperty("idResponsableFor") final Long idResponsableFor,
			@JsonProperty("loginResponsableFor") final String loginResponsableFor,
			@JsonProperty("nameResponsableFor") final String nameResponsableFor) {
		this.id = id;		
		this.processId = processId;
		this.processCode = processCode;
		this.description = description;
		this.idCreatedBy = idCreatedBy;
		this.loginCreatedBy = loginCreatedBy;
		this.idFinishedBy = idFinishedBy;
		this.loginFinishedBy = loginFinishedBy;
		this.idResponsableFor = idResponsableFor;
		this.loginResponsableFor = loginResponsableFor;
		this.nameResponsableFor = nameResponsableFor;
	}
	
	
}
