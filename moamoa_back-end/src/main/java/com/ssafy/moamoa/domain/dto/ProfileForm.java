package com.ssafy.moamoa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileForm {
	private Long id;

	private String nickname;

	private String profileSearchStatus;

	private String img;

	private String context;
}
