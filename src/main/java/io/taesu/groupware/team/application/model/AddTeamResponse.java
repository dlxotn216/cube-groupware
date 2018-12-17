package io.taesu.groupware.team.application.model;

import io.taesu.groupware.common.application.model.BaseDto;
import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
@Data
public class AddTeamResponse extends BaseDto {
	private Long key;
	private String id;
	private String name;
	private List<AddedTeamMember> addedTeamMembers;
	
	private AddTeamResponse(Long key, String id, String name, Boolean deleted, String reason, String description) {
		super(deleted, reason, description);
		this.key = key;
		this.id = id;
		this.name = name;
	}
	
	public static AddTeamResponse from(Team team, List<User> members) {
		AddTeamResponse response
				= new AddTeamResponse(team.getKey(), team.getId(), team.getName(), team.getDeleted(), team.getReason(), team.getDescription());
		response.setAddedTeamMembers(members.stream()
				.map(AddedTeamMember::fromUser)
				.collect(Collectors.toList()));
		return response;
	}
	
	
	@Data
	@AllArgsConstructor
	public static class AddedTeamMember {
		private Long key;
		private String id;
		private String name;
		
		static AddedTeamMember fromUser(User user) {
			return new AddedTeamMember(user.getKey(), user.getId(), user.getName());
		}
	}
	
}
