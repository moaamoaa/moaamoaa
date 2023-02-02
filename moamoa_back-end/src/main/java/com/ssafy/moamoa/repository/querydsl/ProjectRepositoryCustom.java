package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.dto.ProjectDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Project;

public interface ProjectRepositoryCustom {
	List<ProjectDto> search(SearchCondition condition);
	Project getProjectById(Long projectId);

	List<Project> getProjects();
}
