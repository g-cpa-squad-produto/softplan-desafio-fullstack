package br.com.softplan.marcusvoltolim.model.support;

import org.springframework.data.domain.Persistable;

public interface EntityLongId extends Persistable<Long> {
	
	void setId(Long id);
	
}
