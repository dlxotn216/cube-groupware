package io.taesu.groupware.team.domain.service.impl;

import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.team.domain.repository.TeamMemberRepository;
import io.taesu.groupware.team.domain.repository.TeamRepository;
import io.taesu.groupware.team.domain.service.FindTeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
@Service
public class FindTeamServiceImpl implements FindTeamService {
	private TeamRepository teamRepository;
	private TeamMemberRepository teamMemberRepository;
	
	public FindTeamServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {
		this.teamRepository = teamRepository;
		this.teamMemberRepository = teamMemberRepository;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Team findTeamWithMembers(Long teamkey) {
		Team team = teamRepository.findById(teamkey).orElseThrow(IllegalArgumentException::new);
		team.setTeamMembers(teamMemberRepository.findAllByTeamKey(teamkey));
		return team;
	}
}
