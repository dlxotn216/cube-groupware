package io.taesu.groupware.team.application.service;

import io.taesu.groupware.common.interfaces.model.BaseDto;
import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.team.domain.model.TeamMember;
import io.taesu.groupware.team.domain.service.SaveTeamService;
import io.taesu.groupware.team.interfaces.model.AddTeamRequest;
import io.taesu.groupware.team.interfaces.model.AddTeamResponse;
import io.taesu.groupware.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
@Service
public class AddTeamService {
	
	private SaveTeamService saveTeamService;
	private UserRepository userRepository;
	
	public AddTeamService(SaveTeamService saveTeamService, UserRepository userRepository) {
		this.saveTeamService = saveTeamService;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public AddTeamResponse add(AddTeamRequest request) {
		Team newTeam = Team.builder()
				.id(request.getId())
				.name(request.getName())
				.deleted(false)
				.reason(BaseDto.DEFAULT_INITIAL_REASON)
				.description(request.getDescription())
				.build();
		
		newTeam = saveTeamService.save(newTeam);
		
		final Long teamKey = newTeam.getKey();
		List<TeamMember> allTeamMembers
				= saveTeamService.saveMembers(newTeam.getKey(), request.getNewMemberKeys().stream()
				.map(memberKey -> AddTeamRequest.from(teamKey, memberKey))
				.collect(Collectors.toList()));
		
		return AddTeamResponse.from(newTeam, this.userRepository.findAllById(allTeamMembers.stream()
				.map(teamMember -> teamMember.getUser().getKey())
				.collect(Collectors.toList())));
	}
}
