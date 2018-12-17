package io.taesu.groupware.team.application.service;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
public interface AddTeamManagerService {
	void add(Long teamkey, Long userKey);
}
