package process.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class OAuth extends BaseEntity {
	
	@Column
	private String email;
	
	@Column
	private String password;

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
	
}
