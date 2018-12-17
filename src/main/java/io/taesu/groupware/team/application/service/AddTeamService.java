package io.taesu.groupware.team.application.service;

import io.taesu.groupware.team.application.model.AddTeamRequest;
import io.taesu.groupware.team.application.model.AddTeamResponse;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @since 2018-12-14
 */
public interface AddTeamService {
	
	AddTeamResponse add(AddTeamRequest request);
}
