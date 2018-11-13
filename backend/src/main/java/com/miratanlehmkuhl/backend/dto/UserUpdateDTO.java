package com.miratanlehmkuhl.backend.dto;

import javax.validation.constraints.NotNull;

public class UserUpdateDTO {

	@NotNull
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String email;

	@NotNull
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
