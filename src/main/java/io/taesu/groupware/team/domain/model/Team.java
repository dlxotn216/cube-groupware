package io.taesu.groupware.team.domain.model;

import io.taesu.groupware.common.domain.model.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
@Entity(name = "TEAM")
@Getter
@Setter
@Audited
@EntityListeners(value = {AuditingEntityListener.class})
@NoArgsConstructor
public class Team extends BaseEntity {
	@Id
	@Column(name = "TEAM_KEY")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_SEQ")
	@SequenceGenerator(name = "TEAM_SEQ", sequenceName = "TEAM_SEQ")
	private Long key;
	
	@Column(name = "TEAM_ID", unique = true, nullable = false)
	private String id;
	
	@Column(name = "TEAM_NAME", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<TeamMember> teamMembers = new ArrayList<>();
	
	@Builder
	public Team(String id, String name, Boolean deleted, String reason, String description) {
		super(deleted, reason, description);
		this.id = id;
		this.name = name;
		this.teamMembers = new ArrayList<>();
	}
	
	public void addTeamMembers(List<TeamMember> teamMembers) {
		teamMembers.forEach(this::addTeamMember);
	}
	
	public void addTeamMember(TeamMember teamMember) {
		this.teamMembers.add(teamMember);
		teamMember.setTeam(this);
	}
	
	public Optional<TeamMember> getMember(Long userKey) {
		return this.teamMembers.stream()
				.filter(teamMember -> teamMember.getUser().getKey().equals(userKey))
				.findAny();
	}
	
	public void setManager(Long userKey) {
		this.getManager().ifPresent(TeamMember::unsetTeamManager);
		
		this.getMember(userKey)
				.orElseThrow(IllegalArgumentException::new)
				.setTeamManager();
	}
	
	public Optional<TeamMember> getManager() {
		return this.teamMembers.stream()
				.filter(TeamMember::getManager)
				.findAny();
	}
}
