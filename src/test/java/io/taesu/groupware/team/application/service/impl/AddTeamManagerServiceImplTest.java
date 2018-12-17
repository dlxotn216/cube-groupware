package io.taesu.groupware.team.application.service.impl;

import io.taesu.groupware.team.application.model.AddTeamRequest;
import io.taesu.groupware.team.application.model.AddTeamResponse;
import io.taesu.groupware.team.application.service.AddTeamManagerService;
import io.taesu.groupware.team.application.service.AddTeamService;
import io.taesu.groupware.team.domain.model.TeamMember;
import io.taesu.groupware.team.domain.service.FindTeamService;
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
public class AddTeamManagerServiceImplTest {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddTeamService addTeamService;
	
	@Autowired
	private AddTeamManagerService addTeamManagerService;
	
	@Autowired
	private FindTeamService findTeamService;
	
	@Test
	public void 팀장_설정_테스트() {
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
		AddTeamResponse addedTeam = addTeamService.add(addTeamRequest);
		
		//When
		addTeamManagerService.add(addedTeam.getKey(), users.get(0).getKey());
		
		//Then
		TeamMember addedManager = findTeamService.findTeamWithMembers(addedTeam.getKey())
				.getManager()
				.orElseGet(TeamMember::new);
		
		assertThat(addedManager.getKey()).isEqualTo(users.get(0).getKey());
		assertThat(addedManager.getManager()).isEqualTo(true);
		assertThat(findTeamService.findTeamWithMembers(addedTeam.getKey())
									.getTeamMembers()
									.stream()
									.filter(TeamMember::getManager)
									.count())
				.isEqualTo(1L);
	}
	
	private AddTeamRequest getMockAddTeamRequest() {
		AddTeamRequest addTeamRequest = new AddTeamRequest();
		addTeamRequest.setId("Manage Test");
		addTeamRequest.setName("테스트 팀");
		addTeamRequest.setDescription("Test");
		return addTeamRequest;
	}
}