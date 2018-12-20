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
 * @since 2018-12-20
 */
@Getter
public enum VacationReasonOption {
	PERSONAL_REASON("PERSONAL_REASON"),
	
	MEDICAL_CHECKUP_LEAVE("MEDICAL_CHECKUP_LEAVE"),
	
	RESERVE_FORCES("RESERVE_FORCES",
			VacationEvidenceOption.EDUCATION_CERTIFICATE),
	
	CIVIL_DEFENSE("CIVIL_DEFENSE",
			VacationEvidenceOption.EDUCATION_CERTIFICATE),
	
	MARRIAGE("MARRIAGE"),
	
	GRANDPARENTS_FUNERAL("GRANDPARENTS_FUNERAL"),
	
	PARENTS_FUNERAL("PARENTS_FUNERAL"),
	
	ETC("ETC");
	
	private String messageCode;
	
	private Set<VacationEvidenceOption> vacationEvidenceOptions;
	
	VacationReasonOption(String messageCode, VacationEvidenceOption... args) {
		this.messageCode = messageCode;
		List<VacationEvidenceOption> defaultEvidenceOptions = Arrays.asList(args);
		defaultEvidenceOptions.addAll(VacationEvidenceOption.getDefaultEvidenceOptions());
		
		this.vacationEvidenceOptions = new HashSet<>(defaultEvidenceOptions);
	}
	
	public static List<VacationReasonOption> getDefaultVacationReasonOptions(){
		return Arrays.asList(PERSONAL_REASON, ETC);
	}
}
