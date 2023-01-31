package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.dto.ProjectDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.ProjectArea;
import com.ssafy.moamoa.domain.entity.ProjectTechStack;

public interface ProjectRepositoryCustom {
	List<ProjectDto> search(SearchCondition condition);

	List<ProjectTechStack> searchTechStack(SearchCondition condition);

	List<ProjectArea> searchArea(SearchCondition condition);
}
