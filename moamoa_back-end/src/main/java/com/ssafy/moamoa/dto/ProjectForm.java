package com.ssafy.moamoa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectForm {
	// project
	private String title;
	private String contents;
	private String img;
	private String projectStatus;
	private String category;
	private String endDate;
	private int totalPeople;
	private boolean isLocked;

	// team
	private Long userid;

	// project techStack
	private Long[] techStacks;

	// project area
	private Long[] areas;
}
