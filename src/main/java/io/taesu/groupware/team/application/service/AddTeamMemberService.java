package io.taesu.groupware.team.application.service;

import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.team.domain.model.TeamMember;
import io.taesu.groupware.team.domain.service.FindTeamService;
import io.taesu.groupware.team.domain.service.SaveTeamService;
import io.taesu.groupware.team.interfaces.model.AddTeamRequest;
import io.taesu.groupware.team.interfaces.model.AddTeamResponse;
import io.taesu.groupware.user.domain.model.User;
import io.taesu.groupware.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
@Service
public class AddTeamMemberService {
	private FindTeamService findTeamService;
	private SaveTeamService saveTeamService;
	private UserRepository userRepository;
	
	public AddTeamMemberService(FindTeamService findTeamService, SaveTeamService saveTeamService, UserRepository userRepository) {
		this.findTeamService = findTeamService;
		this.saveTeamService = saveTeamService;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public AddTeamResponse add(Long key, List<User> users) {
		if(CollectionUtils.isEmpty(users)) {
			throw new IllegalArgumentException("추가할 사용자 목록이 존재하지 않습니다");
		}
		
		Team team = this.findTeamService.findTeamWithMembers(key);
		
		List<TeamMember> allTeamMembers = saveTeamService.saveMembers(key, users.stream()
				.map(user -> AddTeamRequest.from(key, user.getKey()))
				.collect(Collectors.toList()));
		
		return AddTeamResponse.from(team, this.userRepository.findAllById(allTeamMembers.stream()
				.map(teamMember -> teamMember.getUser().getKey())
				.collect(Collectors.toList())));
	}
}
