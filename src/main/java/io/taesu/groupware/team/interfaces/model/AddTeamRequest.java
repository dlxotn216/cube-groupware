package io.taesu.groupware.team.interfaces.model;

import io.taesu.groupware.common.interfaces.model.BaseDto;
import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.team.domain.model.TeamMember;
import io.taesu.groupware.user.domain.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
@Data
@NoArgsConstructor
public class AddTeamRequest extends BaseDto {
	private String id;
	private String name;
	private List<Long> newMemberKeys;
	
	public AddTeamRequest(Boolean deleted, String reason, String description, String id, String name) {
		super(deleted, reason, description);
		this.id = id;
		this.name = name;
	}
	
	public List<Long> getNewMemberKeys() {
		return CollectionUtils.isEmpty(this.newMemberKeys)
				? Collections.emptyList()
				: this.newMemberKeys;
	}
	
	public static TeamMember from(Long teamKey, Long userKey){
		return TeamMember.builder()
				.user(User.builder().key(userKey).build())
				.team(Team.builder().key(teamKey).build())
				.build();
	}
}
