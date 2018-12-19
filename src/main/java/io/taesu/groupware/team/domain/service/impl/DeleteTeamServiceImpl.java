package io.taesu.groupware.team.domain.service.impl;

import io.taesu.groupware.team.domain.repository.TeamMemberRepository;
import io.taesu.groupware.team.domain.repository.TeamRepository;
import io.taesu.groupware.team.domain.service.DeleteTeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
@Service
public class DeleteTeamServiceImpl implements DeleteTeamService {
	
	private TeamRepository teamRepository;
	private TeamMemberRepository teamMemberRepository;
	
	public DeleteTeamServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {
		this.teamRepository = teamRepository;
		this.teamMemberRepository = teamMemberRepository;
	}
	
	@Transactional
	@Override
	public void delete(Long key) {
		teamMemberRepository.deleteTeamMembersByTeamKeyAsQuery(key);
		teamRepository.deleteTeamByTeamKeyAsQuery(key);
	}
}
