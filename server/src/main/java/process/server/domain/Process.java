package process.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="process", schema = "public")
public class Process {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	protected Long id;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
