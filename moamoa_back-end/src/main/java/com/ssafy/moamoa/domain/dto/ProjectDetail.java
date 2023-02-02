package com.ssafy.moamoa.domain.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.Team;
import com.ssafy.moamoa.domain.entity.TechStack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
	private Long userid;
	private String leaderNickname;
	private boolean isLeader;
	private List<Profile> profiles;

	// project techStack
	private List<TechStack> techStacks;

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

/*	public void setIsLeader(boolean isLeader){
		this.isLeader = isLeader;
	}*/

}
