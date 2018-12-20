package io.taesu.groupware.vacation.domain.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
@Getter
public enum VacationType {
	//오전반차
	MORNING_OFF("MORNING_OFF"),
	
	//오후반차
	AFTERNOON_OFF("AFTERNOON_OFF"),
	
	//연차
	ANNUAL_LEAVE("ANNUAL_LEAVE"),
	
	//병가
	SICK_LEAVE("SICK_LEAVE",
			VacationReasonOption.PERSONAL_REASON),
	
	//건강검진
	MEDICAL_CHECKUP_LEAVE("MEDICAL_CHECKUP_LEAVE",
			VacationReasonOption.MEDICAL_CHECKUP_LEAVE),
	
	//공가
	OFFICIAL_LEAFE("OFFICIAL_LEAFE",
			VacationReasonOption.RESERVE_FORCES,
			VacationReasonOption.CIVIL_DEFENSE),
	
	//공가(오전반차)
	OFFICIAL_MORNING_OFF("OFFICIAL_MORNING_OFF",
			VacationReasonOption.RESERVE_FORCES,
			VacationReasonOption.CIVIL_DEFENSE),
	
	//공가(오후반차)
	OFFICIAL_AFTERNOON_OFF("OFFICIAL_AFTERNOON_OFF",
			VacationReasonOption.RESERVE_FORCES,
			VacationReasonOption.CIVIL_DEFENSE),
	
	//육아휴직
	CHILDCARE_LEAVE("CHILDCARE_LEAVE"),
	
	//휴직
	LEAVE_OF_ABSENSE("LEAVE_OF_ABSENSE"),
	
	//청원휴가
	CONGRATULATION_CONDOLENCE_LEAVE("CONGRATULATION_CONDOLENCE_LEAVE",
			VacationReasonOption.MARRIAGE,
			VacationReasonOption.GRANDPARENTS_FUNERAL,
			VacationReasonOption.PARENTS_FUNERAL),
	
	//출산휴가
	MATERNITY_LEAVE("MATERNITY_LEAVE"),
	
	ETC("ETC");
	
	private String messageCode;
	private Set<VacationReasonOption> vacationReasonOptions;
	
	VacationType(String messageCode, VacationReasonOption... args) {
		this.messageCode = messageCode;
		List<VacationReasonOption> defaultReasonOptions = VacationReasonOption.getDefaultVacationReasonOptions();
		defaultReasonOptions.addAll(Arrays.asList(args));
		this.vacationReasonOptions = new HashSet<>(defaultReasonOptions);
	}
	
	public static Boolean isHalfDayLeave(VacationType vacationType) {
		return vacationType == MORNING_OFF
				|| vacationType == AFTERNOON_OFF
				|| vacationType == OFFICIAL_MORNING_OFF
				|| vacationType == OFFICIAL_AFTERNOON_OFF;
	}
}
