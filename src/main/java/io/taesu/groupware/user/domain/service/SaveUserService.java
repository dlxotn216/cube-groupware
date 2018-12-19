package io.taesu.groupware.user.domain.service;

import io.taesu.groupware.user.domain.model.User;

import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
public interface SaveUserService {
	User save(User user);
	
	List<User> saveAll(List<User> users);
}
