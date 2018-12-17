package io.taesu.groupware.user.domain.repository;

import io.taesu.groupware.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	
}
