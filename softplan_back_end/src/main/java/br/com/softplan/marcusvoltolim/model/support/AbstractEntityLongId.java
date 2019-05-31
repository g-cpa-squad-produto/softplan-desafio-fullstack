package br.com.softplan.marcusvoltolim.model.support;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class AbstractEntityLongId extends AuditableEntity<String> implements EntityLongId {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	protected AbstractEntityLongId() {
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public boolean isNew() {
		return getId() == null;
	}
	
}
