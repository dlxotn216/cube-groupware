package io.taesu.groupware.team.domain.service.impl;

import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.team.domain.model.TeamMember;
import io.taesu.groupware.team.domain.repository.TeamRepository;
import io.taesu.groupware.team.domain.service.SaveTeamService;
import io.taesu.groupware.user.domain.model.User;
import io.taesu.groupware.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
@Service
public class SaveTeamServiceImpl implements SaveTeamService {
	private TeamRepository teamRepository;
	private UserRepository userRepository;
	
	public SaveTeamServiceImpl(TeamRepository teamRepository, UserRepository userRepository) {
		this.teamRepository = teamRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public List<User> saveMembers(Long teamKey, List<Long> memberKeys) {
		Team team = this.teamRepository.findById(teamKey).orElseThrow(IllegalArgumentException::new);
		
		team.addTeamMembers(this.userRepository.findAllById(memberKeys)
				.stream()
				.map(member -> TeamMember.builder().team(team).user(member).build())
				.collect(Collectors.toList()));
		save(team);
		return this.userRepository.findAllById(memberKeys);
	}
	
	@Override
	public Team save(Team team) {
		return teamRepository.save(team);
	}
}
