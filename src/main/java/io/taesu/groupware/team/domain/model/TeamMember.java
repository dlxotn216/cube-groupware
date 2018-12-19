package io.taesu.groupware.team.domain.model;

import io.taesu.groupware.common.domain.model.BaseEntity;
import io.taesu.groupware.user.domain.model.User;
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
@Entity(name = "TeamMember")
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"TEAM_KEY", "USER_KEY", "DEL_FLAG"})
})
@Getter
@Setter
@Audited
@EntityListeners(value = {AuditingEntityListener.class})
@NoArgsConstructor
public class TeamMember extends BaseEntity {
	
	@Id
	@Column(name = "TEAM_MEMBER_KEY")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_MEMBER_SEQ")
	@SequenceGenerator(name = "TEAM_MEMBER_SEQ", sequenceName = "TEAM_MEMBER_SEQ")
	private Long key;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_KEY")
	private Team team;
	
	@ManyToOne
	@JoinColumn(name = "USER_KEY")
	private User user;
	
	@Column(name = "IS_MANAGER", nullable = false)
	private Boolean manager;
	
	@Builder
	public TeamMember(Boolean deleted, String reason, String description, Team team, User user, Boolean manager) {
		super(deleted, reason, description);
		this.team = team;
		this.user = user;
		this.manager = manager == null ? false : manager;
	}
	
	void setTeamManager(){
		this.manager = true;
	}
	
	void unsetTeamManager(){
		this.manager = false;
	}
}
