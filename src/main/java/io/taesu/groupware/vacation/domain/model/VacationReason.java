package io.taesu.groupware.vacation.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-19
 */
@Getter
@Embeddable
@EqualsAndHashCode
public class VacationReason {
	@Column(name = "VACATION_REASON")
	private String reason;
	
	@Column(name = "EVIDENCE")
	private String evidence;
	
	public VacationReason(String reason, String evidence) {
		this.reason = reason;
		this.evidence = evidence;
	}
}
