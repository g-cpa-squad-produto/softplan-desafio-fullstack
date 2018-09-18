package process.server.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UserProcess extends BaseEntity {
	
	@ManyToOne
	private Role role;
	
	@ManyToOne
	private User user;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
