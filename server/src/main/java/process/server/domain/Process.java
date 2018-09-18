package process.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Process extends BaseEntity {
	
	@Column
	private String name;
	
	@Column
	private String code;
	
	@Column
	private String seem;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSeem() {
		return seem;
	}

	public void setSeem(String seem) {
		this.seem = seem;
	}
	
}
