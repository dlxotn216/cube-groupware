package io.taesu.groupware.common.domain.model;

import io.taesu.groupware.common.application.model.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@NoArgsConstructor
@Audited
public class BaseEntity {
	@CreatedBy
	@Column(name = "CREATED_BY", nullable = false, updatable = false)
	private Long createdBy;
	
	@LastModifiedBy
	@Column(name = "UPDATED_BY", nullable = false)
	private Long updatedBy;
	
	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	private ZonedDateTime createdAt;
	
	@Column(name = "UPDATED_AT", nullable = false)
	private ZonedDateTime updatedAt;
	
	@Column(name = "DEL_FLAG", nullable = false)
	private Boolean deleted;
	
	@Column(name = "REASON", nullable = false)
	private String reason;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	public BaseEntity(Boolean deleted, String reason, String description) {
		this.deleted = deleted == null ? false : deleted;
		this.reason = reason == null ? BaseDto.DEFAULT_REASON : reason;
		this.description = description == null ? BaseDto.DEFAULT_DESCRIPTION : description;
	}
	
	@PrePersist
	public void onPrePersist() {
		this.createdAt = ZonedDateTime.now(ZoneOffset.UTC);
		this.updatedAt = this.createdAt;
	}
	
	@PreUpdate
	public void onPreUpdate() {
		this.updatedAt = ZonedDateTime.now(ZoneOffset.UTC);
	}
	
	@Override
	public String toString() {
		return "BaseEntity{" +
				"createdBy=" + createdBy +
				", updatedBy=" + updatedBy +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
