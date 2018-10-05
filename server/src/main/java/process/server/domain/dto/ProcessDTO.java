package process.server.domain.dto;

import java.util.List;

import process.server.domain.Process;
import process.server.domain.User;

public class ProcessDTO {
	
	private Process process;
	
	private List<User> users;

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
