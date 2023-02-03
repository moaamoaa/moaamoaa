package com.ssafy.moamoa.domain.dto;

import java.time.LocalDateTime;

import com.ssafy.moamoa.domain.entity.Apply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApplyForm {

	private Long applyId;
	private Long projectId;
	private Long userId;
	private LocalDateTime time;

	public static ApplyForm toEntity(Apply apply) {
		return ApplyForm.builder()
			.applyId(apply.getId())
			.userId(apply.getUser().getId())
			.projectId(apply.getProject().getId())
			.time(apply.getTime())
			.build();
	}
}
