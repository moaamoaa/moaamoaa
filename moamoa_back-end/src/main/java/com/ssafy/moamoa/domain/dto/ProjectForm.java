package com.ssafy.moamoa.domain.dto;

import com.ssafy.moamoa.domain.entity.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProjectForm {
	// project
	private Long projectId;
	private String title;
	private String contents;
	private String img;
	private String projectStatus;
	private int hit;
	private int countOffer;
	private int totalPeople;
	private int currentPeople;
	private String category;
	private String startDate;
	private String endDate;
	private String createDate;
	private boolean isLocked;

	// team
	private Long userid;
	private boolean isLeader;

	// project techStack
	private Long[] techStacks;

	// project area
	private Long areaId;

	public static ProjectForm toEntity(Project project){
		return ProjectForm.builder()
			.projectId(project.getId())
			.title(project.getTitle())
			.contents(project.getContents())
			.img(project.getImg())
			.projectStatus(String.valueOf(project.getOnoffline()))
			.hit(project.getHit())
			.countOffer(project.getCountOffer())
			.totalPeople(project.getTotalPeople())
			.currentPeople(project.getCurrentPeople())
			.category(String.valueOf(project.getCategory()))
			.startDate(String.valueOf(project.getStartDate()))
			.endDate(String.valueOf(project.getEndDate()))
			.createDate(String.valueOf(project.getCreateDate()))
			.isLocked(project.isLocked())
			.build();
	}

	public ProjectForm() {
	}

	public void setIsLeader(boolean isLeader){
		this.isLeader = isLeader;
	}
}
