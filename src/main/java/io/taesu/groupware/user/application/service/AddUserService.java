package io.taesu.groupware.user.application.service;

import io.taesu.groupware.common.interfaces.model.BaseDto;
import io.taesu.groupware.user.domain.model.User;
import io.taesu.groupware.user.domain.service.SaveUserService;
import io.taesu.groupware.user.interfaces.model.AddUserRequest;
import io.taesu.groupware.user.interfaces.model.AddUserResponse;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
@Service
public class AddUserService {
	private SaveUserService saveUserService;
	
	public AddUserService(SaveUserService saveUserService) {
		this.saveUserService = saveUserService;
	}
	
	public AddUserResponse add(AddUserRequest addUserRequest) {
		return AddUserResponse.from(saveUserService.saveAll(addUserRequest.getAddUserDtos().stream()
																	.map(AddUserRequest::from)
																	.collect(Collectors.toList())));
	}
	
}
