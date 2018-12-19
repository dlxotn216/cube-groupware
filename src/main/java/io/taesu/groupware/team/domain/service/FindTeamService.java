package io.taesu.groupware.team.domain.service;

import io.taesu.groupware.team.domain.model.Team;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
public interface FindTeamService {
	Team findTeamWithMembers(Long key);
}
