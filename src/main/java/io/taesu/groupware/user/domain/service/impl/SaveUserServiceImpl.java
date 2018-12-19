package io.taesu.groupware.user.domain.service.impl;

import io.taesu.groupware.user.domain.model.User;
import io.taesu.groupware.user.domain.repository.UserRepository;
import io.taesu.groupware.user.domain.service.SaveUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
@Service
public class SaveUserServiceImpl implements SaveUserService {
	private UserRepository userRepository;
	
	public SaveUserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public List<User> saveAll(List<User> users) {		
		return userRepository.saveAll(users);
	}
}
