package io.taesu.groupware.common.interfaces.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
@Data
@NoArgsConstructor
public class BaseDto {
	private Boolean deleted;
	
	private String reason;
	
	private String description;
	
	public BaseDto(Boolean deleted, String reason, String description) {
		this.deleted = deleted;
		this.reason = reason;
		this.description = description;
	}
	
	public String getReason() {
		return getOrDefaultValue(this.reason, BaseDto.DEFAULT_REASON);
	}
	
	public String getDescription() {
		return getOrDefaultValue(this.reason, BaseDto.DEFAULT_DESCRIPTION);
	}
	
	public static final String DEFAULT_INITIAL_REASON = "Initial Input.";
	
	public static final String DEFAULT_REASON = "-";
	public static final String DEFAULT_DESCRIPTION = "-";
	
	private static String getOrDefaultValue(String value, String defaultValue) {
		return StringUtils.isEmpty(value) ? defaultValue : value;
	}
}
