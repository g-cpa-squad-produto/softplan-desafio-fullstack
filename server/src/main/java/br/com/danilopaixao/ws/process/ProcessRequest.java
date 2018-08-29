package br.com.danilopaixao.ws.process;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.danilopaixao.ws.legal.advice.LegalAdviceRequest;
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
	
	private String code;
	private String summary;
	private String description;
	private Long idUserCreatedBy;
	private Long idUserFinishedBy;
	private List<LegalAdviceRequest> legalAdvices;
	
	@JsonCreator
	public ProcessRequest(
			@JsonProperty("code") final String code,
			@JsonProperty("summary") final String summary,
			@JsonProperty("description") final String description,
			@JsonProperty("idUserCreatedBy") final Long idUserCreatedBy,
			@JsonProperty("idUserFinishedBy") final Long idUserFinishedBy,
			@JsonProperty("legalAdvices") final List<LegalAdviceRequest> legalAdvices) {
		this.code = code;
		this.summary = summary;
		this.description = description;
		this.idUserCreatedBy = idUserCreatedBy;
		this.idUserFinishedBy = idUserFinishedBy;
		this.legalAdvices = legalAdvices;
	}
}
