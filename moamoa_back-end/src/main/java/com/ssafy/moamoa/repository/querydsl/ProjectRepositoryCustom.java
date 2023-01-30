package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Project;

public interface ProjectRepositoryCustom {
	List<Project> search(SearchCondition condition);
}
