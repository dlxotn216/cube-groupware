package io.taesu.groupware.team.interfaces.model;

import io.taesu.groupware.user.domain.model.User;
import lombok.Data;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
@Data
public class AddTeamManagerResponse {
	private String id;
	private String name;
	
	private AddTeamManagerResponse(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static AddTeamManagerResponse from(User user) {
		return new AddTeamManagerResponse(user.getId(), user.getName());
	}
}
