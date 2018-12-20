package io.taesu.groupware.vacation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-20
 */
@Getter
@AllArgsConstructor
public enum VacationEvidenceOption {
	NO_EVIDENCE("NO_EVIDENCE"),
	EDUCATION_CERTIFICATE("EDUCATION_CERTIFICATE"),
	ETC("ETC")
	;
	
	private String messageCode;
	
	public static List<VacationEvidenceOption> getDefaultEvidenceOptions(){
		return Arrays.asList(NO_EVIDENCE, ETC);
	} 
}
