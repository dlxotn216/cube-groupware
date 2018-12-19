package io.taesu.groupware.vacation.domain.model;

import io.taesu.groupware.common.domain.model.BaseEntity;
import io.taesu.groupware.team.domain.model.TeamMember;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
@Entity
@Getter
@Setter
@Audited
@EntityListeners(value = {AuditingEntityListener.class})
@NoArgsConstructor
public class Vacation extends BaseEntity {
	
	@Id
	@Column(name = "VACATION_KEY")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VACATION_SEQ")
	@SequenceGenerator(name = "VACATION_SEQ", sequenceName = "VACATION_SEQ")
	private Long key;
	
	@ManyToOne
	@JoinColumn(name = "REQUESTER", nullable = false)
	private TeamMember requester;
	
	@ManyToOne
	@JoinColumn(name = "APPROVER")
	private TeamMember approver;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "VACATION_TYPE")
	private VacationType vacationType;
	
	@Column(name = "VACATION_TYPE_ETC")
	private String vacationTypeEtc;
	
	@Embedded
	private VacationPeriod vacationPeriod;
	
	@Embedded
	private VacationReason vacationReason;
	
	@Column(name = "APPROVED_AT")
	private ZonedDateTime approvedAt;
	
	@Column(name = "SIGNATURE")
	private String signature;
	
	@Column(name = "COMMENT")
	private String comment;
}
