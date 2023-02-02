package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.entity.ProjectTechStack;

public interface ProjectTechStackRepositoryCustom {
	List<ProjectTechStack> getAllProjectTechStackByOrder(Long projectId);

	Long deleteAllProjectStackById(Long projectId);

	Long deleteProjectTechStackByOrder(int order);

	ProjectTechStack getProjectTechStack(Long projectId, Long techStackId);

}
