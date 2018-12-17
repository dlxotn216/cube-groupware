package io.taesu.groupware.team.domain.repository;

import io.taesu.groupware.team.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
}
