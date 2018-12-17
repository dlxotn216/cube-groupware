package io.taesu.groupware.team.application.model;

import io.taesu.groupware.common.application.model.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-14
 */
@Data
@NoArgsConstructor
public class AddTeamRequest extends BaseDto {
	private String id;
	private String name;
	private List<Long> newMemberKeys;
	
	public AddTeamRequest(Boolean deleted, String reason, String description, String id, String name) {
		super(deleted, reason, description);
		this.id = id;
		this.name = name;
	}
	
	public List<Long> getNewMemberKeys() {
		return CollectionUtils.isEmpty(this.newMemberKeys)
				? Collections.emptyList()
				: this.newMemberKeys;
	}
}
