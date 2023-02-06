package com.ssafy.moamoa.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.TechStack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProjectDetail {
	// project
	private Long projectId;
	private String title;
	private String contents;
	private String img;
	private String projectStatus;
	private int totalPeople;
	private String category;
	private String startDate;
	private String endDate;

	// team
	private Long userId;
	private String leaderNickname;
	private Long leaderId;
	private boolean isLeader;
	private List<ProfileResultDto> profileResultDtoList;

	// project techStack
	private List<TechStackForm> projectTechStacks;

	// project area
	private Long areaId;

	public static ProjectDetail toEntity(Project project){
		return ProjectDetail.builder()
			.projectId(project.getId())
			.title(project.getTitle())
			.contents(project.getContents())
			.img(project.getImg())
			.projectStatus(String.valueOf(project.getOnoffline()))
			.totalPeople(project.getTotalPeople())
			.category(String.valueOf(project.getCategory()))
			.startDate(String.valueOf(project.getStartDate()))
			.endDate(String.valueOf(project.getEndDate()))
			.build();
	}

	public void setProfileResultDtoList(List<ProfileResultDto> profileResultDtos) {
		this.profileResultDtoList = profileResultDtos;
	}

}
