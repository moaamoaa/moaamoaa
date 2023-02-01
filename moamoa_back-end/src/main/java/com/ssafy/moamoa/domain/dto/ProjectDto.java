package com.ssafy.moamoa.domain.dto;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.moamoa.domain.entity.TechStack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
	public ProjectDto(Long id, String title, String contents, int hit, int totalPeople, int currentPeople) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.hit = hit;
		this.totalPeople = totalPeople;
		this.currentPeople = currentPeople;
	}

}
