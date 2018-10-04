package br.com.softplan.api.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * Usuário do sistema
 * @author Marco
 *
 */
@Data
@Entity
public class User {

	@Id @GeneratedValue
	private Long id;

	@NotBlank(message = "Email required")
	@Email(message = "Email invalid")
	private String email;

	@NotBlank(message = "Password required") 
	@Size(min = 6)
	private String password;

	@Enumerated
	private ProfileEnum profile;
	
	public User() {}

	public User(Long id) {
		setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}

}
