package io.taesu.groupware.user.interfaces.model;

import io.taesu.groupware.common.interfaces.model.BaseDto;
import io.taesu.groupware.user.domain.model.User;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
@Data
public class AddUserResponse {
	private List<AddedUserDto> addUserDtos;
	
	public AddUserResponse(List<AddedUserDto> addUserDtos) {
		this.addUserDtos = addUserDtos;
	}
	
	public static AddUserResponse from(List<User> users) {
		return CollectionUtils.isEmpty(users)
				? new AddUserResponse(Collections.emptyList())
				: new AddUserResponse(users.stream().map(AddUserResponse::from).collect(Collectors.toList()));
	}
	
	private static AddedUserDto from(User user) {
		return new AddedUserDto(user.getDeleted(), user.getReason(), user.getDescription(),
				user.getKey(), user.getId(), user.getName(), user.getEmail(), user.getPhone());
	}
	
	@Data
	public static class AddedUserDto extends BaseDto {
		private Long key;
		private String id;
		private String name;
		private String email;
		private String phone;
		
		public AddedUserDto(Boolean deleted, String reason, String description, Long key, String id, String name, String email, String phone) {
			super(deleted, reason, description);
			this.key = key;
			this.id = id;
			this.name = name;
			this.email = email;
			this.phone = phone;
		}
	}
	
}
