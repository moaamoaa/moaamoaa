package com.ssafy.moamoa.domain.dto;

import java.util.List;

import com.ssafy.moamoa.domain.entity.TechStack;

import lombok.Data;

@Data
public class ProfileDto {
	private Long id;

	private String nickname;

	// private MultipartFile img;

	private String context;

	private List<String> area;

	private List<TechStack> techStacks;

}
