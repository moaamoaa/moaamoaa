package com.ssafy.moamoa.domain.dto;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.moamoa.domain.ProfileOnOffStatus;
import com.ssafy.moamoa.domain.entity.TechStack;

import lombok.Data;

@Data
public class ProfileResultDto {
	private Long id;

	private String nickname;

	// private MultipartFile img;

	private String context;

	private ProfileOnOffStatus status;

	private List<String> area;

	private List<TechStack> techStacks;

	private String cursorId;

	@QueryProjection
	public ProfileResultDto(Long id, String nickname, String context, ProfileOnOffStatus status, String cursorId) {
		this.id = id;
		this.nickname = nickname;
		this.context = context;
		this.status = status;
		this.cursorId = cursorId;
	}

	public ProfileResultDto(Long id, String nickname, String context, ProfileOnOffStatus status) {
		this.id = id;
		this.nickname = nickname;
		this.context = context;
		this.status = status;
	}
}
