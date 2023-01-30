package com.ssafy.moamoa.domain.dto;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.moamoa.domain.entity.TechStack;

import lombok.AllArgsConstructor;

@AllArgsConstructor
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

	@QueryProjection
	public ProjectDto(Long id) {
		this.id = id;

	}
}
