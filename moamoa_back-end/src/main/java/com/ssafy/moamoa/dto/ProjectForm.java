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
	private String projectstatus;
	private String category;
	private String endDate;
	private int countPeople;

	// team
	private Long userid;

	// project techstack
	private Long[] techstacks;

	// project area
	private Long[] areas;
}
