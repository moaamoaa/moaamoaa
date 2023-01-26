package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.ProjectTechStack;

public interface SearchProjectTechStackRepository {
	List<ProjectTechStack> getAllProjectTechStackByOrder(Long projectId);
	Long deleteAllProjectStackById(Long projectId);
}
