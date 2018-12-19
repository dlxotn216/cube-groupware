package io.taesu.groupware.vacation.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
@Getter
@Embeddable
@EqualsAndHashCode
public class VacationPeriod {
	private static final Double FIXED_HALF_DAY_LEAVE_DAYS = 0.5;
	
	@Column(name = "START_AT", nullable = false)
	private LocalDate startAt;
	
	@Column(name = "END_AT", nullable = false)
	private LocalDate endAt;
	
	@Column(name = "DAYS", nullable = false)
	private Double days;
	
	public VacationPeriod(LocalDate startAt, LocalDate endAt, VacationType vacationType) {
		this.startAt = startAt;
		this.endAt = endAt;
		
		validation();
		
		if(VacationType.isHalfDayLeave(vacationType)) {
			this.days = FIXED_HALF_DAY_LEAVE_DAYS;
			this.startAt = this.endAt;
		} else {
			this.days = Double.class.cast(ChronoUnit.DAYS.between(this.startAt, this.endAt));
		}
	}
	
	private void validation() {
		if(this.startAt == null || this.endAt == null) {
			throw new IllegalArgumentException();
		}
		
		if(this.endAt.isBefore(this.startAt)) {
			throw new IllegalArgumentException();
		}
	}
}
