package com.ssafy.moamoa.domain.dto;

import javax.validation.constraints.NotNull;

import com.ssafy.moamoa.domain.entity.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectForm {
	// project
	private Long projectId;
	@NotNull
	private String title;
	private String contents;
	private String img;
	@NotNull
	private String projectStatus;
	private int totalPeople;
	@NotNull
	private String category;
	@NotNull
	private String endDate;

	// team
	private Long userId;

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
			.totalPeople(project.getTotalPeople())
			.category(String.valueOf(project.getCategory()))
			.endDate(String.valueOf(project.getEndDate()))
			.build();
	}
}
