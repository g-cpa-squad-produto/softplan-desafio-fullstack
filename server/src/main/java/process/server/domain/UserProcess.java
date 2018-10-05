package process.server.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_process", schema = "public")
public class UserProcess {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	protected Long id;
	
	@ManyToOne
	private Process process;
	
	@ManyToOne
	private User user;

	public UserProcess() {
		super();
	}
	
	public UserProcess(Process process, User user) {
		super();
		this.process = process;
		this.user = user;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
