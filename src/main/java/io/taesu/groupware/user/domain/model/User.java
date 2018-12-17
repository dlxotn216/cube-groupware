package io.taesu.groupware.user.domain.model;

import io.taesu.groupware.common.domain.model.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
@Entity(name = "USER")
@Getter
@Setter
@Audited
@EntityListeners(value = {AuditingEntityListener.class})
@NoArgsConstructor
public class User extends BaseEntity {
	
	@Id
	@Column(name = "USER_KEY")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ")
	private Long key;
	
	@Column(name = "USER_ID", unique = true, nullable = false)
	private String id;
	
	@Column(name = "USER_NAME", nullable = false)
	private String name;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Builder
	public User(Boolean deleted, String reason, String description, String id, String name, String email, String phone) {
		super(deleted, reason, description);
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
}
