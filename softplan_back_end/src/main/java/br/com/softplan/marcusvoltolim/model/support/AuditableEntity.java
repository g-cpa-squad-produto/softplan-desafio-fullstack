package br.com.softplan.marcusvoltolim.model.support;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<U> {
	
	@CreatedBy
	@Column(nullable = false, updatable = false)
	private U createdBy;
	
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private Date dataCriacao;
	
	@LastModifiedBy
	@Column(nullable = true, insertable = false)
	private U lastModifiedBy;
	
	@LastModifiedDate
	@Column(nullable = true, insertable = false)
	private Date dataModificacao;
	
}
