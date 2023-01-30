package com.ssafy.moamoa.domain.dto;

import java.util.List;

import com.ssafy.moamoa.domain.entity.TechStack;

public class ProjectDto {
	private Long id;

	private String title;

	private String contents;

	// private MultipartFile img;

	private int hit;

	private int totalPeople;

	private int currentPeople;

	private String leaderName;

	private List<TechStack> techStacks;
}
