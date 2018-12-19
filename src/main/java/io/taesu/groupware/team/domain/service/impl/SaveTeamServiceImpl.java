package io.taesu.groupware.team.domain.service.impl;

import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.team.domain.model.TeamMember;
import io.taesu.groupware.team.domain.repository.TeamMemberRepository;
import io.taesu.groupware.team.domain.repository.TeamRepository;
import io.taesu.groupware.team.domain.service.SaveTeamService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
@Service
public class SaveTeamServiceImpl implements SaveTeamService {
	private TeamRepository teamRepository;
	private TeamMemberRepository teamMemberRepository;
	
	public SaveTeamServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {
		this.teamRepository = teamRepository;
		this.teamMemberRepository = teamMemberRepository;
	}
	
	@Override
	public List<TeamMember> saveMembers(Long key, List<TeamMember> teamMembers) {
		Team team = this.teamRepository.findById(key).orElseThrow(IllegalArgumentException::new);
		team.addTeamMembers(teamMembers);
		save(team);
		
		return this.teamMemberRepository.findAllByTeamKey(key);
	}
	
	@Override
	public Team save(Team team) {
		return teamRepository.save(team);
	}
}
