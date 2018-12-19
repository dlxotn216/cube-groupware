package io.taesu.groupware.user.interfaces.model;

import io.taesu.groupware.common.interfaces.model.BaseDto;
import io.taesu.groupware.user.domain.model.User;
import lombok.Data;

import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
@Data
public class AddUserRequest extends BaseDto {
	private List<AddUserDto> addUserDtos;
	
	
	public AddUserRequest(Boolean deleted, String reason, String description, List<AddUserDto> addUserDtos) {
		super(deleted, reason, description);
		this.addUserDtos = addUserDtos;
		this.addUserDtos
				.forEach(addUserDto -> {
					addUserDto.setDeleted(deleted);
					addUserDto.setReason(reason);
					addUserDto.setDescription(description);
				});
	}
	
	public static User from(AddUserDto addUserDto){
		return User.builder()
				.id(addUserDto.id)
				.name(addUserDto.name)
				.email(addUserDto.email)
				.phone(addUserDto.phone)
				.deleted(addUserDto.getDeleted())
				.reason(addUserDto.getReason())
				.description(addUserDto.getDescription())
				.build();
	}
	
	@Data
	public static class AddUserDto extends BaseDto {
		private String id;
		private String name;
		private String email;
		private String phone;
		
		public AddUserDto(String id, String name, String email, String phone) {
			this.id = id;
			this.name = name;
			this.email = email;
			this.phone = phone;
		}
	}
}
