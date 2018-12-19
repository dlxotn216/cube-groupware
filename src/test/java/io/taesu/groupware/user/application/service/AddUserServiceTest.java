package io.taesu.groupware.user.application.service;

import io.taesu.groupware.user.interfaces.model.AddUserRequest;
import io.taesu.groupware.user.interfaces.model.AddUserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lee Tae Su on 2018-12-18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AddUserServiceTest {
	
	@Autowired
	private AddUserService addUserService;
	
	@Test
	public void 사용자_생성_테스트() {
		//Given
		AddUserRequest addUserRequest = getAddUserRequest("taesu", "taesu@crscube.co.kr", "이태수", "010-9995-2723");
		
		//When
		AddUserResponse addUserResponse = addUserService.add(addUserRequest);
		
		//Then
		assertThat(addUserResponse.getAddUserDtos().size()).isEqualTo(addUserRequest.getAddUserDtos().size());
		assertThat(addUserResponse.getAddUserDtos().get(0).getKey()).isNotNull();
		assertThat(addUserResponse.getAddUserDtos().get(0).getId()).isEqualTo(addUserRequest.getAddUserDtos().get(0).getId());
		assertThat(addUserResponse.getAddUserDtos().get(0).getEmail()).isEqualTo(addUserRequest.getAddUserDtos().get(0).getEmail());
		assertThat(addUserResponse.getAddUserDtos().get(0).getName()).isEqualTo(addUserRequest.getAddUserDtos().get(0).getName());
	}
	
	private AddUserRequest getAddUserRequest(String id, String email, String name, String phone) {
		return new AddUserRequest(
				false
				, "Initial input"
				, "-"
				, Arrays.asList(new AddUserRequest.AddUserDto(id, name, email, phone))
		);
	}
}