package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.entity.ProjectTechStack;

public interface QProjectTechStackRepository {
	List<ProjectTechStack> getAllProjectTechStackByOrder(Long projectId);

	Long deleteAllProjectStackById(Long projectId);
}
