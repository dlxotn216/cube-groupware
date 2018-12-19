package io.taesu.groupware.team.application.service.impl;

import io.taesu.groupware.common.interfaces.model.BaseDto;
import io.taesu.groupware.team.application.service.AddTeamService;
import io.taesu.groupware.team.interfaces.model.AddTeamRequest;
import io.taesu.groupware.team.interfaces.model.AddTeamResponse;
import io.taesu.groupware.user.domain.model.User;
import io.taesu.groupware.user.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Lee Tae Su on 2018-12-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AddTeamServiceTest {
	
	@Autowired
	private AddTeamService addTeamService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void 생성_테스트() {
		//Given
		AddTeamRequest addTeamRequest = getMockAddTeamRequest();
		
		//When
		AddTeamResponse addedTeam = addTeamService.add(addTeamRequest);
		
		//Then
		assertThat(addedTeam.getKey()).isNotNull();
		assertThat(addedTeam.getId()).isEqualTo(addTeamRequest.getId());
		assertThat(addedTeam.getName()).isEqualTo(addTeamRequest.getName());
		assertThat(addedTeam.getReason()).isEqualTo(BaseDto.DEFAULT_INITIAL_REASON);
	}
	
	@Test
	public void 멤버와_함께_생성_테스트() {
		//Given
		List<User> users = Arrays.asList(
				User.builder().id("taesu1").email("taesu1@crscube.co.kr").name("이태수1").phone("0101111").build(),
				User.builder().id("taesu2").email("taesu2@crscube.co.kr").name("이태수2").phone("0102222").build(),
				User.builder().id("taesu3").email("taesu3@crscube.co.kr").name("이태수3").phone("0103333").build(),
				User.builder().id("taesu4").email("taesu4@crscube.co.kr").name("이태수4").phone("0104444").build()
		);
		users = userRepository.saveAll(users);
		AddTeamRequest addTeamRequest = getMockAddTeamRequest();
		addTeamRequest.setNewMemberKeys(users.stream().map(User::getKey).collect(Collectors.toList()));
		
		//When
		AddTeamResponse addedTeam = addTeamService.add(addTeamRequest);
		
		//Then
		assertThat(addedTeam.getAddedTeamMembers().size()).isEqualTo(users.size());
		assertThat(addedTeam.getAddedTeamMembers().get(0).getId()).isEqualTo(users.get(0).getId());
		assertThat(addedTeam.getAddedTeamMembers().get(1).getId()).isEqualTo(users.get(1).getId());
		assertThat(addedTeam.getAddedTeamMembers().get(2).getId()).isEqualTo(users.get(2).getId());
		assertThat(addedTeam.getAddedTeamMembers().get(3).getId()).isEqualTo(users.get(3).getId());
	}
	
	private AddTeamRequest getMockAddTeamRequest() {
		AddTeamRequest addTeamRequest = new AddTeamRequest();
		addTeamRequest.setId("TEST");
		addTeamRequest.setName("테스트 팀");
		addTeamRequest.setDescription("Test");
		return addTeamRequest;
	}
}