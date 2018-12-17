package io.taesu.groupware.team.domain.repository;

import io.taesu.groupware.team.domain.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
	List<TeamMember> findAllByTeamKey(Long taemKey);
}
