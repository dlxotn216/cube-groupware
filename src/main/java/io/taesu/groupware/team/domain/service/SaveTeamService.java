package io.taesu.groupware.team.domain.service;

import io.taesu.groupware.team.domain.model.Team;
import io.taesu.groupware.user.domain.model.User;

import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
public interface SaveTeamService {
	List<User> saveMembers(Long teamkey, List<Long> memberKeys);
	
	Team save(Team team);
}
