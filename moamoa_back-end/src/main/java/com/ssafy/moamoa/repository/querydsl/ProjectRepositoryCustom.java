package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.dto.ProjectDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;

public interface ProjectRepositoryCustom {
	List<ProjectDto> search(SearchCondition condition);

}
