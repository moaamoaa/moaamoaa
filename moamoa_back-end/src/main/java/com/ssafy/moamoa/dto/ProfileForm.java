package com.ssafy.moamoa.dto;

import com.ssafy.moamoa.domain.ProfileSearchStatus;

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
