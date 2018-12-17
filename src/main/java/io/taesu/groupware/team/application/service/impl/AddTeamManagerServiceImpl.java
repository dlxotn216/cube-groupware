package io.taesu.groupware.team.application.service.impl;

import io.taesu.groupware.team.application.service.AddTeamManagerService;
import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.team.domain.service.FindTeamService;
import io.taesu.groupware.team.domain.service.SaveTeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
@Service
public class AddTeamManagerServiceImpl implements AddTeamManagerService {
	private SaveTeamService saveTeamService;
	private FindTeamService findTeamService;
	
	public AddTeamManagerServiceImpl(SaveTeamService saveTeamService, FindTeamService findTeamService) {
		this.saveTeamService = saveTeamService;
		this.findTeamService = findTeamService;
	}
	
	@Transactional
	@Override
	public void add(Long teamkey, Long userKey) {
		Team teamWithMembers = findTeamService.findTeamWithMembers(teamkey);
		teamWithMembers.setManager(userKey);
		
		saveTeamService.save(teamWithMembers);
	}
}
