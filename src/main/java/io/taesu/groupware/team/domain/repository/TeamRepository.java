package io.taesu.groupware.team.domain.repository;

import io.taesu.groupware.team.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
	@Transactional
	@Modifying
	@Query(value = "UPDATE Team t SET t.deleted = true WHERE t.key = :teamKey")
	void deleteTeamByTeamKeyAsQuery(@Param("teamKey") Long teamKey);
}
