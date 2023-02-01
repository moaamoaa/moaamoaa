package com.ssafy.moamoa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileForm {
	private Long id; // userId

	private String nickname;

	private String profileSearchStatus;

	private String profileOnOffStatus;

	private String img;

	private String context;

}
