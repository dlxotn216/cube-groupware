package io.taesu.groupware.team.application.service;

import io.taesu.groupware.team.domain.repository.TeamMemberRepository;
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
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lee Tae Su on 2018-12-18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AddTeamMemberServiceTest {
	@Autowired
	private AddTeamService addTeamService;
	
	@Autowired
	private TeamMemberRepository teamMemberRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddTeamMemberService addTeamMemberService;
	
	@Test(expected = IllegalArgumentException.class)
	public void 빈_사용자_추가_테스트() {
		//Given
		AddTeamRequest addTeamRequest = getMockAddTeamRequest();
		AddTeamResponse addedTeam = addTeamService.add(addTeamRequest);
		List<User> users = Collections.emptyList();
		
		//When
		AddTeamResponse add = addTeamMemberService.add(addedTeam.getKey(), users);
		
		//Then
		//IllegalArgumentException이 발생하여 Then 절은 실행되지 않을 것.
		assertThat(1).isEqualTo(0);
	}
	
	@Test
	public void 팀원_최초_추가_테스트() {
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
		
		//When
		AddTeamResponse add = addTeamMemberService.add(addedTeam.getKey(), users);
		
		//Then
		assertThat(teamMemberRepository.findAllByTeamKey(addedTeam.getKey()).size())
				.isEqualTo(users.size());
	}
	
	@Test
	public void 팀원_추가_테스트() {
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
		
		
		//when
		List<User> anotherUsers = Arrays.asList(
				User.builder().id("taesu5").email("taesu5@crscube.co.kr").name("이태수5").phone("0101111").build(),
				User.builder().id("taesu6").email("taesu6@crscube.co.kr").name("이태수6").phone("0102222").build()
		);
		anotherUsers = userRepository.saveAll(anotherUsers);
		addTeamMemberService.add(addedTeam.getKey(), anotherUsers);
		
		//Then
		assertThat(teamMemberRepository.findAllByTeamKey(addedTeam.getKey()).size())
				.isEqualTo(users.size() + anotherUsers.size());
	}
	
	@Test
	public void 삭제된_사용자_미조회_테스트() {
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
		users.get(0).setDeleted(true);
		users.get(1).setDeleted(true);
		userRepository.saveAll(users);
		
		//Then
		assertThat(teamMemberRepository.findAllByTeamKey(addedTeam.getKey()).size())
				.isEqualTo(2);
		
	}
	
	private AddTeamRequest getMockAddTeamRequest() {
		AddTeamRequest addTeamRequest = new AddTeamRequest();
		addTeamRequest.setId("TEST FOR INITIAL");
		addTeamRequest.setName("테스트 팀");
		addTeamRequest.setDescription("Test");
		return addTeamRequest;
	}
}