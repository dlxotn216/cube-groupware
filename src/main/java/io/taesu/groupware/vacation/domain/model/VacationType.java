package io.taesu.groupware.vacation.domain.model;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-18
 */
public enum VacationType {	
	MORNING_OFF,                    	//오전반차
	AFTERNOON_OFF,                    	//오후반차
	ANNUAL_LEAVE,                    	//연차
	SICK_LEAVE,                    		//병가
	MEDICAL_CHECKUP_LEAVE,            	//건강검진
	OFFICIAL_LEAFE,                    	//공가
	OFFICIAL_MORNING_OFF,				//공가(오전반차)
	OFFICIAL_AFTERNOON_OFF,			//공가(오후반차)
	CHILDCARE_LEAVE,                	//유가휴직
	LEAVE_OF_ABSENSE,                	//휴직
	CONGRATULATION_CONDOLENCE_LEAVE, 	//청원휴가
	MATERNITY_LEAVE, 					//출산휴가
	ETC
	;
	
	public static Boolean isHalfDayLeave(VacationType vacationType){
		return vacationType == MORNING_OFF
				|| vacationType == AFTERNOON_OFF
				|| vacationType == OFFICIAL_MORNING_OFF
				|| vacationType == OFFICIAL_AFTERNOON_OFF;
	}
}
