package io.taesu.groupware.team.application.service;

import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.team.domain.service.DeleteTeamService;
import io.taesu.groupware.team.domain.service.FindTeamService;
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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lee Tae Su on 2018-12-18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTeamServiceTest {
	
	@Autowired
	private AddTeamService addTeamService;
	
	@Autowired
	private DeleteTeamService deleteTeamService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddTeamMemberService addTeamMemberService;
	
	@Autowired
	private FindTeamService findTeamService;
	
	@Test
	public void 팀_삭제_테스트(){
		//Given
		AddTeamRequest addTeamRequest = getMockAddTeamRequest();
		AddTeamResponse addedTeam = addTeamService.add(addTeamRequest);
		List<User> users = Arrays.asList(
				User.builder().id("taesu1").email("taesu1@crscube.co.kr").name("이태수1").phone("0101111").build(),
				User.builder().id("taesu2").email("taesu2@crscube.co.kr").name("이태수2").phone("0102222").build(),
				User.builder().id("taesu3").email("taesu3@crscube.co.kr").name("이태수3").phone("0103333").build(),
				User.builder().id("taesu4").email("taesu4@crscube.co.kr").name("이태수4").phone("0104444").build()
		);
		users = userRepository.saveAll(users);
		addTeamMemberService.add(addedTeam.getKey(), users);
		
		//When
		deleteTeamService.delete(addedTeam.getKey());
		
		//Then
		Team teamWithMembers = findTeamService.findTeamWithMembers(addedTeam.getKey());
		assertThat(teamWithMembers.getDeleted()).isEqualTo(true);
		assertThat(teamWithMembers.getTeamMembers()
				.stream()
				.filter(teamMember -> teamMember.getDeleted().equals(false))
				.count()).isEqualTo(0);
	}
	
	private AddTeamRequest getMockAddTeamRequest() {
		AddTeamRequest addTeamRequest = new AddTeamRequest();
		addTeamRequest.setId("TEST FOR DELETE");
		addTeamRequest.setName("테스트 팀");
		addTeamRequest.setDescription("Test");
		return addTeamRequest;
	}
}