package com.miratanlehmkuhl.backend.dto;

import javax.validation.constraints.NotNull;

public class UserUpdateDTO extends UserNewDTO {
	
	@NotNull
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
