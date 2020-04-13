package com.ldutra.processos.api.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProcessoDTO {

	private Long id;
	private String parecer;
	private Integer mes;
	private Integer ano;
	private Long usuario;
	private String status;
}