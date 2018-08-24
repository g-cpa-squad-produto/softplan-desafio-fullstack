package br.com.danilopaixao.ws.core;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<I> implements Serializable {

	private static final long serialVersionUID = 7408483307531670855L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private I id;

	@Version
	@Column(name = "version")
	private Long version;

	@Column(name = "created_at", nullable = false)
	private ZonedDateTime createdAt;

	@Column(name = "updated_at")
	private ZonedDateTime updatedAt;

	@Column(name = "removed_at")
	private ZonedDateTime removedAt;

	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;

	@LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;

	@PrePersist
    public void setCreationDate() {
        this.createdAt = ZonedDateTime.now();
    }

	@PreUpdate
    public void setUpdatingDate() {
        this.updatedAt = ZonedDateTime.now();
    }

	@PreRemove
    public void setChangeDate() {
        this.removedAt = ZonedDateTime.now();
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null || ! this.getClass().isInstance(obj)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		@SuppressWarnings("unchecked")
		final BaseEntity<I> other = (BaseEntity<I>) obj;
		
		if (other.getId() == null) {
			return false;
		}
		
		return new EqualsBuilder()
				.append(this.getId(), other.getId())
				.append(this.getVersion(), other.getVersion())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).append(this.getVersion()).append(this.getCreatedAt())
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(this.getId()).append(this.getVersion()).append(this.getCreatedAt())
				.toString();
	}

}
