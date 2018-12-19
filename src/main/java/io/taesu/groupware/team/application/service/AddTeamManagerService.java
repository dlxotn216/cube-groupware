package io.taesu.groupware.team.application.service;

import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.team.domain.service.FindTeamService;
import io.taesu.groupware.team.domain.service.SaveTeamService;
import io.taesu.groupware.team.interfaces.model.AddTeamManagerResponse;
import io.taesu.groupware.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
@Service
public class AddTeamManagerService {
	private SaveTeamService saveTeamService;
	private FindTeamService findTeamService;
	private UserRepository userRepository;
	
	public AddTeamManagerService(SaveTeamService saveTeamService, FindTeamService findTeamService, UserRepository userRepository) {
		this.saveTeamService = saveTeamService;
		this.findTeamService = findTeamService;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public AddTeamManagerResponse add(Long teamKey, Long userKey) {
		Team teamWithMembers = this.findTeamService.findTeamWithMembers(teamKey);
		teamWithMembers.setManager(userKey);
		
		this.saveTeamService.save(teamWithMembers);
		return this.userRepository.findById(userKey)
				.map(AddTeamManagerResponse::from).orElseThrow(IllegalArgumentException::new);
	}
}
