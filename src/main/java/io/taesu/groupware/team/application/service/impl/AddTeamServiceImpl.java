package io.taesu.groupware.team.application.service.impl;

import io.taesu.groupware.common.application.model.BaseDto;
import io.taesu.groupware.team.application.model.AddTeamRequest;
import io.taesu.groupware.team.application.model.AddTeamResponse;
import io.taesu.groupware.team.application.service.AddTeamService;
import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.team.domain.service.SaveTeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
@Service
public class AddTeamServiceImpl implements AddTeamService {
	
	private SaveTeamService saveTeamService;
	
	public AddTeamServiceImpl(SaveTeamService saveTeamService) {
		this.saveTeamService = saveTeamService;
	}
	
	@Override
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
		return AddTeamResponse.from(newTeam, saveTeamService.saveMembers(newTeam.getKey(), request.getNewMemberKeys()));
	}
}
