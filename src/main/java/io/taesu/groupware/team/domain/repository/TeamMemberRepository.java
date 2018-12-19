package io.taesu.groupware.team.domain.repository;

import io.taesu.groupware.team.domain.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-17
 */
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
	/**
	 * {@code key}에 해당하는 팀의 모든 팀원 조회
	 *
	 * @param teamKey Team key
	 * @return 팀원 목록
	 */
	@Query(value = "SELECT t FROM TeamMember t INNER JOIN t.user u WHERE t.team.key = :teamKey AND u.deleted = false")
	List<TeamMember> findAllByTeamKey(@Param("teamKey") Long teamKey);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE TeamMember t SET t.deleted = true WHERE t.team.key = :teamKey")
	void deleteTeamMembersByTeamKeyAsQuery(@Param("teamKey") Long teamKey);
}
